package com.epam.film.rating.connectionpool;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public final class ConnectionPool {
    private String databaseURL;
    private String userName;
    private String password;
    private int poolSize;
    private BlockingQueue<Connection> availableConnections;
    private BlockingQueue<Connection> usedConnections;

    private ConnectionPool() {

        DBResourceManager dbResourceManager = DBResourceManager.getInstance();

        this.availableConnections = new ArrayBlockingQueue<>(Integer.parseInt(dbResourceManager.getValue((String.valueOf(DBParameter.DB_POOL_SIZE)))));
        this.usedConnections = new ArrayBlockingQueue<>(Integer.parseInt(dbResourceManager.getValue((String.valueOf(DBParameter.DB_POOL_SIZE)))));
        this.databaseURL = dbResourceManager.getValue(DBParameter.DB_URL);
        this.userName = dbResourceManager.getValue(DBParameter.DB_USER);
        this.password = dbResourceManager.getValue(DBParameter.DB_PASSWORD);
        this.poolSize = Integer.parseInt(dbResourceManager.getValue((String.valueOf(DBParameter.DB_POOL_SIZE))));

        initAvailableConnections();
    }

    private static class ConnectionPoolHolder {
        public static final ConnectionPool INSTANCE = new ConnectionPool();
    }

    public static ConnectionPool getInstance() {
        return ConnectionPoolHolder.INSTANCE;
    }

    public Connection getConnection() {
        try {
            Connection connection = availableConnections.poll(1500, TimeUnit.MILLISECONDS);
            if(connection != null) {
                usedConnections.add(connection);
                return connection;
            } else {
                return null;
            }
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Timeout of getting available connection exceeded", e);
        }

    }

    private void initAvailableConnections() {
        for (int i = 0; i < poolSize; i++) {
            availableConnections.add(createConnection());
        }
    }

    public boolean releaseConnection(Connection connection) throws SQLException {
        if(connection == null) {
            return false;
        }
        if(!usedConnections.remove(connection)) {
            return false;
        }
        return availableConnections.add(connection);
    }

    private PooledConnection createConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseURL,userName,password);
            return new PooledConnection(connection);
        } catch (SQLException | ClassNotFoundException e) {
            throw new ConnectionPoolException("Initialization of connection pool went wrong", e);
        }

    }

    public void closeConnectionQueue() throws SQLException {
        Connection connection = null;

        for (Connection usedConnection : usedConnections) {
            releaseConnection(usedConnection);
        }
        while ((connection = availableConnections.poll()) != null) {
            if(!connection.getAutoCommit()) {
                connection.commit();
            }
            ((PooledConnection) connection).reallyClose();
        }
    }

    public void closeConnection (ResultSet resultSet, Statement statement, Connection connection) {
        try {
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection (ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        try {
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection (PreparedStatement preparedStatement, Connection connection) {

        try {
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection (Statement statement, Connection connection) {

        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private class PooledConnection implements Connection {
        private final Connection connection;

        public PooledConnection(Connection connection) throws SQLException {
            this.connection = connection;
            this.connection.setAutoCommit(true);
        }

        public void reallyClose() throws SQLException {
            connection.close();
        }

        @Override
        public Statement createStatement() throws SQLException {
            return connection.createStatement();
        }

        @Override
        public PreparedStatement prepareStatement(String s) throws SQLException {
            return connection.prepareStatement(s);
        }

        @Override
        public void close() throws SQLException {
            releaseConnection(this);
        }

        @Override
        public CallableStatement prepareCall(String s) throws SQLException {
            return connection.prepareCall(s);
        }

        @Override
        public String nativeSQL(String s) throws SQLException {
            return connection.nativeSQL(s);
        }

        @Override
        public void setAutoCommit(boolean b) throws SQLException {
            connection.setAutoCommit(b);
        }

        @Override
        public boolean getAutoCommit() throws SQLException {
            return connection.getAutoCommit();
        }

        @Override
        public void commit() throws SQLException {
            connection.commit();
        }

        @Override
        public void rollback() throws SQLException {
            connection.rollback();
        }

        @Override
        public boolean isClosed() throws SQLException {
            return connection.isClosed();
        }

        @Override
        public DatabaseMetaData getMetaData() throws SQLException {
            return connection.getMetaData();
        }

        @Override
        public void setReadOnly(boolean b) throws SQLException {
            connection.setReadOnly(b);
        }

        @Override
        public boolean isReadOnly() throws SQLException {
            return connection.isReadOnly();
        }

        @Override
        public void setCatalog(String s) throws SQLException {
            connection.setCatalog(s);
        }

        @Override
        public String getCatalog() throws SQLException {
            return connection.getCatalog();
        }

        @Override
        public void setTransactionIsolation(int i) throws SQLException {
            connection.setTransactionIsolation(i);
        }

        @Override
        public int getTransactionIsolation() throws SQLException {
            return connection.getTransactionIsolation();
        }

        @Override
        public SQLWarning getWarnings() throws SQLException {
            return connection.getWarnings();
        }

        @Override
        public void clearWarnings() throws SQLException {
            connection.clearWarnings();
        }

        @Override
        public Statement createStatement(int i, int i1) throws SQLException {
            return connection.createStatement(i, i1);
        }

        @Override
        public PreparedStatement prepareStatement(String s, int i, int i1) throws SQLException {
            return connection.prepareStatement(s, i1);
        }

        @Override
        public CallableStatement prepareCall(String s, int i, int i1) throws SQLException {
            return connection.prepareCall(s, i, i1);
        }

        @Override
        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return connection.getTypeMap();
        }

        @Override
        public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
            connection.setTypeMap(map);
        }

        @Override
        public void setHoldability(int i) throws SQLException {
            connection.setHoldability(i);
        }

        @Override
        public int getHoldability() throws SQLException {
            return connection.getHoldability();
        }

        @Override
        public Savepoint setSavepoint() throws SQLException {
            return connection.setSavepoint();
        }

        @Override
        public Savepoint setSavepoint(String s) throws SQLException {
            return connection.setSavepoint(s);
        }

        @Override
        public void rollback(Savepoint savepoint) throws SQLException {
            connection.rollback(savepoint);
        }

        @Override
        public void releaseSavepoint(Savepoint savepoint) throws SQLException {
            connection.releaseSavepoint(savepoint);
        }

        @Override
        public Statement createStatement(int i, int i1, int i2) throws SQLException {
            return connection.createStatement(i, i1, i2);
        }

        @Override
        public PreparedStatement prepareStatement(String s, int i, int i1, int i2) throws SQLException {
            return connection.prepareStatement(s, i, i1, i2);
        }

        @Override
        public CallableStatement prepareCall(String s, int i, int i1, int i2) throws SQLException {
            return connection.prepareCall(s, i, i1, i2);
        }

        @Override
        public PreparedStatement prepareStatement(String s, int i) throws SQLException {
            return connection.prepareStatement(s, i);
        }

        @Override
        public PreparedStatement prepareStatement(String s, int[] ints) throws SQLException {
            return connection.prepareStatement(s, ints);
        }

        @Override
        public PreparedStatement prepareStatement(String s, String[] strings) throws SQLException {
            return connection.prepareStatement(s, strings);
        }

        @Override
        public Clob createClob() throws SQLException {
            return connection.createClob();
        }

        @Override
        public Blob createBlob() throws SQLException {
            return connection.createBlob();
        }

        @Override
        public NClob createNClob() throws SQLException {
            return connection.createNClob();
        }

        @Override
        public SQLXML createSQLXML() throws SQLException {
            return connection.createSQLXML();
        }

        @Override
        public boolean isValid(int i) throws SQLException {
            return connection.isValid(i);
        }

        @Override
        public void setClientInfo(String s, String s1) throws SQLClientInfoException {
            connection.setClientInfo(s, s1);
        }

        @Override
        public void setClientInfo(Properties properties) throws SQLClientInfoException {
            connection.setClientInfo(properties);
        }

        @Override
        public String getClientInfo(String s) throws SQLException {
            return connection.getClientInfo(s);
        }

        @Override
        public Properties getClientInfo() throws SQLException {
            return connection.getClientInfo();
        }

        @Override
        public Array createArrayOf(String s, Object[] objects) throws SQLException {
            return connection.createArrayOf(s, objects);
        }

        @Override
        public Struct createStruct(String s, Object[] objects) throws SQLException {
            return connection.createStruct(s, objects);
        }

        @Override
        public void setSchema(String s) throws SQLException {
            connection.setSchema(s);
        }

        @Override
        public String getSchema() throws SQLException {
            return connection.getSchema();
        }

        @Override
        public void abort(Executor executor) throws SQLException {
            connection.abort(executor);
        }

        @Override
        public void setNetworkTimeout(Executor executor, int i) throws SQLException {
            connection.setNetworkTimeout(executor, i);
        }

        @Override
        public int getNetworkTimeout() throws SQLException {
            return connection.getNetworkTimeout();
        }

        @Override
        public <T> T unwrap(Class<T> aClass) throws SQLException {
            return connection.unwrap(aClass);
        }

        @Override
        public boolean isWrapperFor(Class<?> aClass) throws SQLException {
            return connection.isWrapperFor(aClass);
        }
    }
}