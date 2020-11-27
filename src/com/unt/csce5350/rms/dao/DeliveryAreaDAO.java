package com.unt.csce5350.rms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unt.csce5350.rms.updated.model.Deliveryarea;
import com.unt.csce5350.rms.utils.DBConnectionUtil;


/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table deliveryAreas in the database.
 * 
 * @author Jerin Joseph
 *
 */
public class DeliveryAreaDAO {
    private static final String INSERT_DELIVERYAREA_SQL = "INSERT INTO deliveryarea" + "  (DeliveryAreaName, DeliveryAreaZip ) VALUES " +
        " (?, ?);";

    private static final String SELECT_DELIVERYAREA_BY_ID = "select DeliveryAreaID, DeliveryAreaName, DeliveryAreaZip from deliveryarea where DeliveryAreaID =?";
    private static final String SELECT_ALL_DELIVERYAREA = "select * from deliveryarea";
    private static final String DELETE_DELIVERYAREA_SQL = "delete from deliveryarea where DeliveryAreaID = ?;";
    private static final String UPDATE_DELIVERYAREA_SQL = "update deliveryarea set DeliveryAreaName =?, DeliveryAreaZip=? where DeliveryAreaID = ?;";

    public DeliveryAreaDAO() {}

    public void insertDeliveryArea(Deliveryarea deliveryArea) throws SQLException {
        System.out.println(INSERT_DELIVERYAREA_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DBConnectionUtil.getConnection(); 
        		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DELIVERYAREA_SQL)) {
            preparedStatement.setString(1, deliveryArea.getDeliveryAreaName());
            preparedStatement.setInt(2, deliveryArea.getDeliveryAreaZip());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        	DBConnectionUtil.printSQLException(e);
        }
    }

    public Deliveryarea selectDeliveryArea(int id) {
        Deliveryarea deliveryArea = null;
        // Step 1: Establishing a Connection
        try (Connection connection = DBConnectionUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DELIVERYAREA_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String deliveryAreaName = rs.getString("DeliveryAreaName");
                int deliveryAreaZip = rs.getInt("DeliveryAreaZip");
                
                deliveryArea = new Deliveryarea(id, deliveryAreaName, deliveryAreaZip);
            }
        } catch (SQLException e) {
        	DBConnectionUtil.printSQLException(e);
        }
        return deliveryArea;
    }

    public List < Deliveryarea > selectAllDeliveryAreas() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Deliveryarea > deliveryAreas = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = DBConnectionUtil.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DELIVERYAREA);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("DeliveryAreaID");
                String deliveryAreaName = rs.getString("DeliveryAreaName");
                int deliveryAreaZip = rs.getInt("DeliveryAreaZip");

                deliveryAreas.add(new Deliveryarea(id, deliveryAreaName, deliveryAreaZip));
            }
        } catch (SQLException e) {
        	DBConnectionUtil.printSQLException(e);
        }
        return deliveryAreas;
    }

    public boolean deleteDeliveryArea(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DBConnectionUtil.getConnection(); 
        		PreparedStatement statement = connection.prepareStatement(DELETE_DELIVERYAREA_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateDeliveryArea(Deliveryarea deliveryArea) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DBConnectionUtil.getConnection(); 
        		PreparedStatement statement = connection.prepareStatement(UPDATE_DELIVERYAREA_SQL);) {
            statement.setString(1, deliveryArea.getDeliveryAreaName());
            statement.setInt(2, deliveryArea.getDeliveryAreaZip());
            statement.setInt(3, deliveryArea.getDeliveryAreaID());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

}