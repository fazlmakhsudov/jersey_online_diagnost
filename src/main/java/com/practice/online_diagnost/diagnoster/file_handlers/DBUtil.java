package com.practice.online_diagnost.diagnoster.file_handlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private final String jdbcURL;
    private final String jdbcUsername;
    private final String jdbcPassword;
    private Connection jdbcConnection;

    public Connection getJdbcConnection() {
        return jdbcConnection;
    }

    public DBUtil(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    public void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                    jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    public void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

//    public static void main(String[] args) throws SQLException{
//        DBUtil dbUtil = new DBUtil(DBInfo.getJdbcURL(), DBInfo.getJdbcUsername(), DBInfo.getJdbcPassword());
//        dbUtil.connect();
//        System.out.println(dbUtil.getJdbcConnection().isClosed());
//        Statement st = dbUtil.getJdbcConnection().createStatement();
//        ResultSet rs = st.executeQuery("select * from dihotomic_table;");
//        while (rs.next()) {
//            System.out.println(rs.getInt(1) + "   " + rs.getString(2) + "  " + rs.getString(4));
//        }
//    }

}
