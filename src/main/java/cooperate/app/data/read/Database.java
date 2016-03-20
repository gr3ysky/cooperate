package cooperate.app.data.read;

import cooperate.infrastructure.dto.NotMapped;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PreDestroy;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.*;
import java.sql.Date;
import java.util.*;

@Repository
public class Database {
    @Autowired
    private SessionFactory sessionFactory;
    private Connection connection;
    private HashMap<String, Object> outputvalues = new HashMap<String, Object>() {
    };

    private static void setParameter(CallableStatement statement, String type, int ordinal, int size, Object value) throws Exception {
        type = type.toUpperCase(Locale.ENGLISH);
        if (value == null) {
            statement.setNull(ordinal, getDbType(type));
            return;
        }
        if (type.equals("INT") || type.equals("SMALLINT")) {
            statement.setInt(ordinal, Integer.valueOf(value.toString()));
            return;
        } else if (type.equals("CHARACTER") || type.equals("VARCHAR") || type.equals("LONGVARCHAR")) {
            statement.setString(ordinal, value.toString());
            return;
        } else if (type.equals("NUMERIC") || type.equals("DECIMAL")) {
            statement.setBigDecimal(ordinal, BigDecimal.valueOf(Long.valueOf(value.toString())));
            return;
        } else if (type.equals("BIGINT")) {
            statement.setLong(ordinal, Long.valueOf(value.toString()));
            return;
        } else if (type.equals("DATE")) {
            statement.setDate(ordinal, Date.valueOf(value.toString()));
            return;
        } else if (type.equals("BIT") && size == 0) {
            statement.setBoolean(ordinal, Boolean.parseBoolean(value.toString().equals("1") ? "true" : "false"));
            return;
        } else if (type.equals("TINYINT") && size > 0) {
            statement.setByte(ordinal, Byte.parseByte(value.toString()));
            return;
        } else
            throw new Exception(String.format("Parameter type is not implemented %s", type));
    }

    private static int getDbType(String type) throws Exception {
        type = type.toUpperCase(Locale.ENGLISH);
        if (type.equals("INT"))
            return Types.INTEGER;
        else if (type.equals("SMALLINT"))
            return Types.SMALLINT;
        else if (type.equals("CHARACTER"))
            return Types.CHAR;
        else if (type.equals("VARCHAR"))
            return Types.VARCHAR;
        else if (type.equals("LONGVARCHAR"))
            return Types.LONGNVARCHAR;
        else if (type.equals("NUMERIC"))
            return Types.NUMERIC;
        else if (type.equals("DECIMAL"))
            return Types.DECIMAL;
        else if (type.equals("BIGINT"))
            return Types.BIGINT;
        else if (type.equals("BIT"))
            return Types.BIT;
        else if (type.equals("TINYINT"))
            return Types.TINYINT;
        else if (type.equals("DATE"))
            return Types.DATE;
        else
            throw new Exception(String.format("Dbtype %s is not implemented.", type));
    }

    private static String getSql(String procedure, Object[] parameters, List<SqlParameterDefinition> sqlParameterDefinitions) throws Exception {
        if (parameters != null && sqlParameterDefinitions.size() != parameters.length) {
            throw new Exception(String.format("Parameters count for procedure %s is not matching. provided parameter count is %d, but procedure has %d parameters", procedure, parameters.length, sqlParameterDefinitions.size()));
        }
        StringBuilder sb = new StringBuilder().append("{call ").append(procedure).append("(");
        for (int i = 0; i < parameters.length; i++) {
            sb.append("?,");
        }
        if (parameters.length > 0)
            sb.deleteCharAt(sb.length() - 1);
        sb.append(")}");
        return sb.toString();
    }

    private static boolean setProperty(Class<?> clazz, Object object, String fieldName, Object fieldValue) {

        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(object, fieldValue);
                return true;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return false;
    }

    protected Object SetNull(int val) {
        if (val == -1)
            return null;
        return val;
    }

    private Connection getConncetion() {
        if (connection != null) return connection;
        SessionImplementor sessionImplementor = (SessionImplementor) sessionFactory.getCurrentSession();
        return sessionImplementor.connection();
    }

    private List<SqlParameterDefinition> getParamterDefinitions(String procedure) throws SQLException {
        String sql = new StringBuilder().append("SELECT PARAMETER_NAME,PARAMETER_MODE, DATA_TYPE,ORDINAL_POSITION,CHARACTER_MAXIMUM_LENGTH")
                .append(" FROM information_schema.parameters")
                .append(" WHERE SPECIFIC_NAME ='").append(procedure).append("' AND ROUTINE_TYPE = 'PROCEDURE'")
                .append(" ORDER BY  ORDINAL_POSITION;").toString();

        Connection connection = getConncetion();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery(sql);
        List<SqlParameterDefinition> parameterDefinationList = new ArrayList<SqlParameterDefinition>();
        while (resultSet.next()) {
            SqlParameterDefinition sqlParameterDefination = new SqlParameterDefinition();
            sqlParameterDefination.setParameterName(resultSet.getString("PARAMETER_NAME"));
            sqlParameterDefination.setOrdinal(resultSet.getInt("ORDINAL_POSITION"));
            sqlParameterDefination.setSize(resultSet.getInt("CHARACTER_MAXIMUM_LENGTH"));
            sqlParameterDefination.setType(resultSet.getString("DATA_TYPE"));
            sqlParameterDefination.setMode(resultSet.getString("PARAMETER_MODE"));
            parameterDefinationList.add(sqlParameterDefination);
        }
        resultSet.close();
        return parameterDefinationList;
    }

    @PreDestroy
    protected void destroy() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    protected <T> T exetuteScalar(String procedure, Object... parameters) throws Exception {
        CallableStatement statement = getStatement(procedure, parameters);
        ResultSet rs = statement.executeQuery();
        T t = null;
        try {
            t = (T) rs.getObject(0);
            setOutputValues(rs);
        } finally {
            rs.close();
            destroy();
        }
        return t;
    }

    protected <T> T exetuteReader(Class<T> clazz, String procedure, Object... parameters) throws Exception {
        CallableStatement statement = getStatement(procedure, parameters);
        ResultSet rs = statement.executeQuery();
        T t = null;
        Boolean first = true;
        try {
            while (rs.next() && first) {
                t = MapObjectFromResultSet(clazz, rs);
                first = false;
            }
            setOutputValues(rs);
        } finally {
            rs.close();
            destroy();
        }

        return t;
    }

    private void setOutputValues(ResultSet rs) throws SQLException {
        if (outputvalues == null) return;
        CallableStatement statement = (CallableStatement) rs.getStatement();
        for (Map.Entry<String, Object> entry : outputvalues.entrySet()) {
            String key = entry.getKey();
            entry.setValue(statement.getObject(key));
        }
    }

    protected <T> List<T> exetuteListReader(Class<T> clazz, String procedure, Object... parameters) throws Exception {
        CallableStatement statement = getStatement(procedure, parameters);
        ResultSet rs = statement.executeQuery();
        List<T> list = new ArrayList<T>();
        try {
            while (rs.next()) {
                T t = MapObjectFromResultSet(clazz, rs);
                if (t != null)
                    list.add(t);
            }
            setOutputValues(rs);
        } finally {
            rs.close();
            destroy();
        }
        return list;
    }

    protected Object getOutputValue(String parameterName) {
        return outputvalues.get(parameterName);
    }

    private <T> T MapObjectFromResultSet(Class<T> clazz, ResultSet rs) throws IllegalAccessException, InstantiationException {
        T t = null;
        for (Field field : clazz.getDeclaredFields()) {
            try {
                if (t == null)
                    t = clazz.newInstance();
                if (field.getDeclaredAnnotation(NotMapped.class) != null) continue;
                Object value = rs.getObject(field.getName());
                if (value != null)
                    setProperty(clazz, t, field.getName(), value);
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            } finally {
                //TODO: do nothing
            }

        }
        return t;
    }


    private CallableStatement getStatement(String procedure, Object[] parameters) throws Exception {
        List<SqlParameterDefinition> sqlParameterDefinitions = getParamterDefinitions(procedure);
        CallableStatement statement = this.getConncetion().prepareCall(getSql(procedure, parameters, sqlParameterDefinitions));
        int i = 0;
        for (SqlParameterDefinition sqlParameterDefinition : sqlParameterDefinitions) {
            ParseAndSetParameter(statement, sqlParameterDefinition, parameters[i]);
            i++;
        }
        return statement;
    }

    private void ParseAndSetParameter(CallableStatement statement, SqlParameterDefinition sqlParameterDefinition, Object value) throws Exception {
        String type = sqlParameterDefinition.getType();
        int size = sqlParameterDefinition.getSize();
        int ordinal = sqlParameterDefinition.getOrdinal();
        if (sqlParameterDefinition.getMode().equals("IN")) {
            setParameter(statement, type, ordinal, size, value);
        } else if (sqlParameterDefinition.getMode().equals("OUT")) {
            statement.registerOutParameter(sqlParameterDefinition.getOrdinal(), getDbType(type));
            AddOutputParameter(sqlParameterDefinition.getParameterName());
        } else { //INOUT
            statement.registerOutParameter(sqlParameterDefinition.getOrdinal(), getDbType(type));
            setParameter(statement, type, ordinal, size, value);
            AddOutputParameter(sqlParameterDefinition.getParameterName());
        }
    }

    private void AddOutputParameter(String parameter) {
        if (outputvalues == null)
            outputvalues = new HashMap<String, Object>();
        outputvalues.put(parameter, null);
    }

}
