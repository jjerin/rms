package com.unt.csce5350.rms.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {
	
    public static  Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(RMSConstants.jdbcDriverClass);
            connection = DriverManager.getConnection(RMSConstants.jdbcURL, RMSConstants.jdbcUserName, RMSConstants.jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    
    
    public static void printSQLException(SQLException ex) {
    	ex.printStackTrace();
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
