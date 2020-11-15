package com.unt.csce5350.rms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unt.csce5350.rms.model.Menu;


/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table menus in the database.
 * 
 * @author Jerin Joseph
 *
 */
public class MenuDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/jerin?useSSL=false&allowPublicKeyRetrieval=true";
    private String jdbcUserName = "root";
    private String jdbcPassword = "jerin";

    private static final String INSERT_MENUS_SQL = "INSERT INTO menus" + "  (name, email, country) VALUES " +
        " (?, ?, ?);";

    private static final String SELECT_MENU_BY_ID = "select id,name,email,country from menus where id =?";
    private static final String SELECT_ALL_MENUS = "select * from menus";
    private static final String DELETE_MENUS_SQL = "delete from menus where id = ?;";
    private static final String UPDATE_MENUS_SQL = "update menus set name = ?,email= ?, country =? where id = ?;";

    public MenuDAO() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public void insertMenu(Menu menu) throws SQLException {
        System.out.println(INSERT_MENUS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MENUS_SQL)) {
            preparedStatement.setString(1, menu.getName());
            preparedStatement.setString(2, menu.getEmail());
            preparedStatement.setString(3, menu.getCountry());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Menu selectMenu(int id) {
        Menu menu = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MENU_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                menu = new Menu(id, name, email, country);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return menu;
    }

    public List < Menu > selectAllMenus() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Menu > menus = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MENUS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                menus.add(new Menu(id, name, email, country));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return menus;
    }

    public boolean deleteMenu(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_MENUS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateMenu(Menu menu) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_MENUS_SQL);) {
            statement.setString(1, menu.getName());
            statement.setString(2, menu.getEmail());
            statement.setString(3, menu.getCountry());
            statement.setInt(4, menu.getId());

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