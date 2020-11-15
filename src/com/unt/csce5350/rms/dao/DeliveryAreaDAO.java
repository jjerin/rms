package com.unt.csce5350.rms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unt.csce5350.rms.model.DeliveryArea;


/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table deliveryAreas in the database.
 * 
 * @author Jerin Joseph
 *
 */
public class DeliveryAreaDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/jerin?useSSL=false&allowPublicKeyRetrieval=true";
    private String jdbcDeliveryAreaname = "root";
    private String jdbcPassword = "jerin";

    private static final String INSERT_DELIVERYAREA_SQL = "INSERT INTO deliveryAreas" + "  (name, email, country) VALUES " +
        " (?, ?, ?);";

    private static final String SELECT_DELIVERYAREA_BY_ID = "select id,name,email,country from deliveryAreas where id =?";
    private static final String SELECT_ALL_DELIVERYAREA = "select * from deliveryAreas";
    private static final String DELETE_DELIVERYAREA_SQL = "delete from deliveryAreas where id = ?;";
    private static final String UPDATE_DELIVERYAREA_SQL = "update deliveryAreas set name = ?,email= ?, country =? where id = ?;";

    public DeliveryAreaDAO() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcDeliveryAreaname, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public void insertDeliveryArea(DeliveryArea deliveryArea) throws SQLException {
        System.out.println(INSERT_DELIVERYAREA_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DELIVERYAREA_SQL)) {
            preparedStatement.setString(1, deliveryArea.getName());
            preparedStatement.setString(2, deliveryArea.getEmail());
            preparedStatement.setString(3, deliveryArea.getCountry());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public DeliveryArea selectDeliveryArea(int id) {
        DeliveryArea deliveryArea = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DELIVERYAREA_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                deliveryArea = new DeliveryArea(id, name, email, country);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return deliveryArea;
    }

    public List < DeliveryArea > selectAllDeliveryAreas() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < DeliveryArea > deliveryAreas = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DELIVERYAREA);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                deliveryAreas.add(new DeliveryArea(id, name, email, country));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return deliveryAreas;
    }

    public boolean deleteDeliveryArea(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_DELIVERYAREA_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateDeliveryArea(DeliveryArea deliveryArea) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_DELIVERYAREA_SQL);) {
            statement.setString(1, deliveryArea.getName());
            statement.setString(2, deliveryArea.getEmail());
            statement.setString(3, deliveryArea.getCountry());
            statement.setInt(4, deliveryArea.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
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