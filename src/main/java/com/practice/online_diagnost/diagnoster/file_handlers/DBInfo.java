package com.practice.online_diagnost.diagnoster.file_handlers;

public class DBInfo {
    private static final String jdbcURL;
    private static final String jdbcUsername;
    private static final String jdbcPassword;

    static {
        jdbcURL = "jdbc:mysql://localhost:3306/diplomadb";
        jdbcUsername = "mfh";
        jdbcPassword = "111111";
    }

    public static String getJdbcURL() {
        return jdbcURL;
    }

    public static String getJdbcUsername() {
        return jdbcUsername;
    }

    public static String getJdbcPassword() {
        return jdbcPassword;
    }
}
