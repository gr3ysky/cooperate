package cooperate.app.data.read;

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
        } else if (type.equals("BIT") && size == 1) {
            statement.setBoolean(ordinal, Boolean.getBoolean(value.toString()));
            return;
        } else if (type.equals("TINYINT") && size > 0) {
            statement.setByte(ordinal, Byte.parseByte(value.toString()));
            return;
        } else
            throw new Exception(String.format("Parameter type is not implemented %s", type));
    }

    private static JDBCType getDbType(String type) throws Exception {
        type = type.toUpperCase(Locale.ENGLISH);
        if (type.equals("INT"))
            return JDBCType.INTEGER;
        else if (type.equals("SMALLINT"))
            return JDBCType.SMALLINT;
        else if (type.equals("CHARACTER"))
            return JDBCType.CHAR;
        else if (type.equals("VARCHAR"))
            return JDBCType.VARCHAR;
        else if (type.equals("LONGVARCHAR"))
            return JDBCType.LONGNVARCHAR;
        else if (type.equals("NUMERIC"))
            return JDBCType.NUMERIC;
        else if (type.equals("DECIMAL"))
            return JDBCType.DECIMAL;
        else if (type.equals("BIGINT"))
            return JDBCType.BIGINT;
        else if (type.equals("BIT"))
            return JDBCType.BIT;
        else if (type.equals("TINYINT"))
            return JDBCType.TINYINT;
        else if (type.equals("DATE"))
            return JDBCType.DATE;
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
                if (field.getType().equals(boolean.class)) {
                    if (fieldValue.toString().equals("1"))
                        field.set(object, true);
                    else
                        field.set(object, false);
                } else {
                    field.set(object, fieldValue);
                }
                return true;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return false;
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
        outputvalues = null;
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
        CallableStatement statement = (CallableStatement) rs.getStatement();
        if (outputvalues == null)
            outputvalues = new HashMap<String, Object>() {
            };
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
                list.add(MapObjectFromResultSet(clazz, rs));
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
        T t = clazz.newInstance();
        for (Field field : clazz.getDeclaredFields()) {
            try {
                Object value = rs.getObject(field.getName());
                if (value != null)
                    setProperty(clazz, t, field.getName(), value);
            } catch (SQLException e) {
                e.printStackTrace();
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
        } else { //INOUT
            statement.registerOutParameter(sqlParameterDefinition.getOrdinal(), getDbType(type));
            setParameter(statement, type, ordinal, size, value);
            outputvalues.put(sqlParameterDefinition.getParameterName(), null);
        }
    }

}
