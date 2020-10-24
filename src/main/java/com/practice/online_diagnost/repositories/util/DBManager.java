package com.practice.online_diagnost.repositories.util;

import com.practice.online_diagnost.exceptions.Messages;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

import javax.sql.DataSource;
import java.sql.*;
import java.util.OptionalInt;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBManager {
    private static DBManager dbManager;
    private final String JDBC_DRIVER;
    private final String JDBC_DB_URL;
    private final String JDBC_USER;
    private final String JDBC_PASSWORD;
    private static GenericObjectPool gPool = null;
    private static final Logger LOGGER = Logger.getLogger("DBUtil.class");
    private final DataSource dataSource;

    private DBManager() throws Exception {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(Path.PROPERTY_FILE);
        this.JDBC_DRIVER = resourceBundle.getString("jdbcDriver");
        this.JDBC_DB_URL = String.format("%s%s%s%s", resourceBundle.getString("jdbcUrl"), '/', resourceBundle.getString("jdbcDatabase"),
                "?useTimezone=true&serverTimezone=UTC");
        this.JDBC_USER = resourceBundle.getString("jdbcUserName");
        this.JDBC_PASSWORD = resourceBundle.getString("jdbcPassword");
        LOGGER.log(Level.INFO, "ConnectionPool has been initialised with params ({0}, {1}, {2}, {3})",
                new String[]{LOGGER.getName(), this.JDBC_DRIVER, this.JDBC_DB_URL, this.JDBC_USER, this.JDBC_PASSWORD});
        OptionalInt optionalInt = OptionalInt.of(Integer.parseInt(resourceBundle.getString("maxTotalConn")));
        this.dataSource = setUpPool(optionalInt.orElse(1));
        LOGGER.log(Level.INFO, "DataSource object has been initialized: {0}",
                new String[]{printDbStatus()});
        LOGGER.info("DBUtil object has been instantiated");
    }

    public static DBManager getInstance() throws Exception {
        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
    }

    public Connection getConnectionFromPool() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException throwables) {
            LOGGER.severe(throwables.getMessage());
        }
        LOGGER.info(printDbStatus());
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
           LOGGER.severe(e.getMessage());
        }
        return connection;
    }


    public  static void releaseConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException throwables) {
            LOGGER.severe(throwables.getMessage());
        }
        LOGGER.info(printDbStatus());
    }

    /**
     * Rollbacks a connection.
     *
     * @param con Connection to be rollbacked.
     */
    public static void rollback(final Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                LOGGER.severe(Messages.CANNOT_ROLLBACK_TRANSACTION);
            }
        }
    }

    private DataSource setUpPool(int maxTotalConn) throws Exception {
        Class.forName(JDBC_DRIVER);
        gPool = new GenericObjectPool();
        gPool.setMaxActive(maxTotalConn);
        ConnectionFactory cf = new DriverManagerConnectionFactory(JDBC_DB_URL, JDBC_USER, JDBC_PASSWORD);
        PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf, gPool, null, null,
                false, true);
        return new PoolingDataSource(gPool);
    }

    private static String printDbStatus() {
        return "{ Max.: " + gPool.getMaxActive() + "; Active: " + gPool.getNumActive() + ";" +
                " Idle: " + gPool.getNumIdle() + " }";
    }
}
