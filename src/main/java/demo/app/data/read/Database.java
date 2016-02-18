package demo.app.data.read;

import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class Database {
    @Autowired
    private SessionFactory sessionFactory;
    private Connection connection;

    Database() {
        this.connection = getConncetion();
    }

    private Connection getConncetion() {
        if (connection != null) return connection;
        SessionImplementor sessionImplementor = (SessionImplementor) sessionFactory.getCurrentSession();
        return sessionImplementor.connection();
    }

    private List<SqlParameterDefination> getParamterDefinations(String procedure) throws SQLException {
        String sql = new StringBuilder().append("SELECT PARAMETER_NAME,PARAMETER_MODE, DATA_TYPE,ORDINAL_POSITION,CHARACTER_MAXIMUM_LENGTH")
                .append(" FROM information_schema.parameters")
                .append(" WHERE SPECIFIC_NAME = '?' AND ROUTINE_TYPE = 'PROCEDURE'")
                .append(" ORDER BY  ORDINAL_POSITION;").toString();

        Connection connection = getConncetion();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, procedure);
        ResultSet resultSet = statement.executeQuery(sql);
        List<SqlParameterDefination> parameterDefinationList = new ArrayList<SqlParameterDefination>();
        while (resultSet.next()) {
            SqlParameterDefination sqlParameterDefination = new SqlParameterDefination();
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
        if (!getConncetion().isClosed()) {
            getConncetion().close();
        }
    }

    public <T> T exetuteScalar(String procedure, Object[] parameters) throws Exception {
        PreparedStatement statement = getStatement(procedure, parameters);
        ResultSet rs = statement.executeQuery();
        T t = (T) rs.getObject(0);
        rs.close();
        destroy();
        return t;
    }

    private PreparedStatement getStatement(String procedure, Object[] parameters) throws Exception {
        List<SqlParameterDefination> sqlParameterDefinations = getParamterDefinations(procedure);
        if (parameters != null && sqlParameterDefinations.size() != parameters.length) {
            throw new Exception(String.format("Parameters count for procedure %s is not matching. provided parameter count is %d, but procedure has %d parameters", procedure, parameters.length, sqlParameterDefinations.size()));
        }
        StringBuilder sb = new StringBuilder().append("{call ").append(procedure).append("(");
        for (int i = 0; i < parameters.length; i++) {
            sb.append("?,");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        PreparedStatement statement = getConncetion().prepareStatement(sb.toString());
        for (SqlParameterDefination sqlParameterDefination : sqlParameterDefinations) {
            //TODO: add parameters
        }
        return statement;

    }
}
