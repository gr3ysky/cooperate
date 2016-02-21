package cooperate.app.data.read;

import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PreDestroy;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Database {
    @Autowired
    private SessionFactory sessionFactory;
    private Connection connection;
    private HashMap<String, Object> outputvalues = new HashMap<String, Object>() {
    };

    Database() {
        this.connection = getConncetion();
    }

    private static void setParameter(CallableStatement statement, String type, int ordinal, int size, Object value) throws Exception {
        if (type == "INT" || type == "SMALLINT")
            statement.setInt(ordinal, Integer.valueOf(value.toString()));
        else if (type == "CHARACTER" || type == "VARCHAR" || type == "LONGVARCHAR")
            statement.setString(ordinal, value.toString());
        else if (type == "NUMERIC" || type == "DECIMAL")
            statement.setBigDecimal(ordinal, BigDecimal.valueOf(Long.valueOf(value.toString())));
        else if (type == "BIGINT")
            statement.setLong(ordinal, Long.valueOf(value.toString()));
        else if (type == "DATE")
            statement.setDate(ordinal, Date.valueOf(value.toString()));
        if (type == "BIT" && size == 1)
            statement.setBoolean(ordinal, Boolean.getBoolean(value.toString()));
        if (type == "TINYINT" && size > 0)
            statement.setByte(ordinal, Byte.parseByte(value.toString()));
        else
            throw new Exception(String.format("Parameter type is not implemented %s", type));
    }

    private static JDBCType getDbType(String type) throws Exception {
        if (type == "INT")
            return JDBCType.INTEGER;
        else if (type == "SMALLINT")
            return JDBCType.SMALLINT;
        else if (type == "CHARACTER")
            return JDBCType.CHAR;
        else if (type == "VARCHAR")
            return JDBCType.VARCHAR;
        else if (type == "LONGVARCHAR")
            return JDBCType.LONGNVARCHAR;
        else if (type == "NUMERIC")
            return JDBCType.NUMERIC;
        else if (type == "DECIMAL")
            return JDBCType.DECIMAL;
        else if (type == "BIGINT")
            return JDBCType.BIGINT;
        else if (type == "BIT")
            return JDBCType.BIT;
        else if (type == "TINYINT")
            return JDBCType.TINYINT;
        else if (type == "DATE")
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
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }

    public static boolean setProperty(Object object, String fieldName, Object fieldValue) {
        Class<?> clazz = object.getClass();
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

    private Connection getConncetion() {
        if (connection != null) return connection;
        SessionImplementor sessionImplementor = (SessionImplementor) sessionFactory.getCurrentSession();
        return sessionImplementor.connection();
    }

    private List<SqlParameterDefinition> getParamterDefinitions(String procedure) throws SQLException {
        String sql = new StringBuilder().append("SELECT PARAMETER_NAME,PARAMETER_MODE, DATA_TYPE,ORDINAL_POSITION,CHARACTER_MAXIMUM_LENGTH")
                .append(" FROM information_schema.parameters")
                .append(" WHERE SPECIFIC_NAME = '?' AND ROUTINE_TYPE = 'PROCEDURE'")
                .append(" ORDER BY  ORDINAL_POSITION;").toString();

        Connection connection = getConncetion();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, procedure);
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
        connection.close();
        return parameterDefinationList;
    }

    @PreDestroy
    public void destroy() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
        outputvalues = null;
    }

    public <T> T exetuteScalar(String procedure, Object[] parameters) throws Exception {
        CallableStatement statement = getStatement(procedure, parameters);
        ResultSet rs = statement.executeQuery();
        T t = (T) rs.getObject(0);
        setOutputValues(rs);
        rs.close();
        destroy();
        return t;
    }

    public <T> T exetuteReader(Class<T> clazz, String procedure, Object[] parameters) throws Exception {
        CallableStatement statement = getStatement(procedure, parameters);
        ResultSet rs = statement.executeQuery();
        T t = null;
        Boolean first = true;
        while (rs.next() && first) {
            t = MapObjectFromResultSet(clazz, rs);
            first = false;
        }
        setOutputValues(rs);
        rs.close();
        destroy();
        return t;
    }

    private void setOutputValues(ResultSet rs) throws SQLException {
        CallableStatement statement = (CallableStatement) rs.getStatement();
        for (Map.Entry<String, Object> entry : outputvalues.entrySet()) {
            String key = entry.getKey();
            entry.setValue(statement.getObject(key));
        }
    }

    public <T> List<T> exetuteListReader(Class<T> clazz, String procedure, Object[] parameters) throws Exception {
        CallableStatement statement = getStatement(procedure, parameters);
        ResultSet rs = statement.executeQuery();
        List<T> list = new ArrayList<T>();
        while (rs.next()) {
            list.add(MapObjectFromResultSet(clazz, rs));
        }
        setOutputValues(rs);
        rs.close();
        destroy();
        return list;
    }

    public Object getOutputValue(String parameterName) {
        return outputvalues.get(parameterName);
    }

    private <T> T MapObjectFromResultSet(Class<T> clazz, ResultSet rs) throws SQLException, IllegalAccessException, InstantiationException {
        T t = clazz.newInstance();
        for (Field field : clazz.getFields()) {
            if (rs.findColumn(field.getName()) > 0) {
                Object value = rs.getObject(field.getName());
                if (value != null)
                    setProperty(t, field.getName(), value);
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
        if (sqlParameterDefinition.getMode() == "IN") {
            setParameter(statement, type, ordinal, size, value);
        } else if (sqlParameterDefinition.getMode() == "OUT") {
            statement.registerOutParameter(sqlParameterDefinition.getOrdinal(), getDbType(type));
        } else { //INOUT
            statement.registerOutParameter(sqlParameterDefinition.getOrdinal(), getDbType(type));
            setParameter(statement, type, ordinal, size, value);
            outputvalues.put(sqlParameterDefinition.getParameterName(), null);
        }
    }

}
