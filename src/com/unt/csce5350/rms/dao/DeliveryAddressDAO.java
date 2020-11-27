package com.unt.csce5350.rms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.unt.csce5350.rms.updated.model.Deliveryaddress;
import com.unt.csce5350.rms.utils.DBConnectionUtil;


/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table deliveryAddresss in the database.
 * 
 * @author Jerin Joseph
 *
 */
public class DeliveryAddressDAO {
    private static final String INSERT_DELIVERYADDRESS_SQL = "INSERT INTO deliveryaddress" + "  (DeliveryAddressStreet1, DeliveryAddressStreet2, DeliveryAddressCity, DeliveryAddressState, DeliveryAddressZip ) VALUES " +
        " (?, ?, ?, ?, ?);";

    private static final String SELECT_DELIVERYADDRESS_BY_ID = "select * from deliveryaddress where DeliveryAddressID =?";
    private static final String SELECT_ALL_DELIVERYADDRESS = "select * from deliveryaddress";
    private static final String DELETE_DELIVERYADDRESS_SQL = "delete from deliveryaddress where DeliveryAddressID = ?;";
    private static final String UPDATE_DELIVERYADDRESS_SQL = "update deliveryaddress set DeliveryAddressStreet1 =?, DeliveryAddressStreet2 =?, DeliveryAddressCity =?, DeliveryAddressState =?, DeliveryAddressZip =? where DeliveryAddressID = ?;";

    public DeliveryAddressDAO() {}

    public int insertDeliveryAddress(Deliveryaddress deliveryAddress) throws SQLException {
        System.out.println(INSERT_DELIVERYADDRESS_SQL);
        int deliveryAddressId = 0;
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DBConnectionUtil.getConnection(); 
        		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DELIVERYADDRESS_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, deliveryAddress.getDeliveryAddressStreet1());
            preparedStatement.setString(2, deliveryAddress.getDeliveryAddressStreet2());
            preparedStatement.setString(3, deliveryAddress.getDeliveryAddressCity());
            preparedStatement.setString(4, deliveryAddress.getDeliveryAddressState());
            preparedStatement.setString(5, deliveryAddress.getDeliveryAddressZip());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
            	deliveryAddressId=rs.getInt(1);
            }

            System.out.println("deliveryAddressId: "+deliveryAddressId);
            
        } catch (SQLException e) {
        	DBConnectionUtil.printSQLException(e);
        }
        return deliveryAddressId;
   }

    public Deliveryaddress selectDeliveryAddress(int id) {
        Deliveryaddress deliveryAddress = null;
        // Step 1: Establishing a Connection
        try (Connection connection = DBConnectionUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DELIVERYADDRESS_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String deliveryAddressStreet1 = rs.getString("DeliveryAddressStreet1");
                String deliveryAddressStreet2 = rs.getString("DeliveryAddressStreet2");
                String deliveryAddressCity = rs.getString("DeliveryAddressCity");
                String deliveryAddressState = rs.getString("DeliveryAddressState");
                String deliveryAddressZip = rs.getString("DeliveryAddressZip");
                
                deliveryAddress = new Deliveryaddress(id, deliveryAddressCity, deliveryAddressState, deliveryAddressStreet1,
            			deliveryAddressStreet2, deliveryAddressZip);
            }
        } catch (SQLException e) {
        	DBConnectionUtil.printSQLException(e);
        }
        return deliveryAddress;
    }

    public List < Deliveryaddress > selectAllDeliveryAddresss() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Deliveryaddress > deliveryAddresss = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = DBConnectionUtil.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DELIVERYADDRESS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("DeliveryAddressID");
                String deliveryAddressStreet1 = rs.getString("DeliveryAddressStreet1");
                String deliveryAddressStreet2 = rs.getString("DeliveryAddressStreet2");
                String deliveryAddressCity = rs.getString("DeliveryAddressCity");
                String deliveryAddressState = rs.getString("DeliveryAddressState");
                String deliveryAddressZip = rs.getString("DeliveryAddressZip");
                
                deliveryAddresss.add(new Deliveryaddress(id, deliveryAddressCity, deliveryAddressState, deliveryAddressStreet1,
            			deliveryAddressStreet2, deliveryAddressZip));
            }
        } catch (SQLException e) {
        	DBConnectionUtil.printSQLException(e);
        }
        return deliveryAddresss;
    }

    public boolean deleteDeliveryAddress(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DBConnectionUtil.getConnection(); 
        		PreparedStatement statement = connection.prepareStatement(DELETE_DELIVERYADDRESS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateDeliveryAddress(Deliveryaddress deliveryAddress) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DBConnectionUtil.getConnection(); 
        		PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DELIVERYADDRESS_SQL);) {
            preparedStatement.setString(1, deliveryAddress.getDeliveryAddressStreet1());
            preparedStatement.setString(2, deliveryAddress.getDeliveryAddressStreet2());
            preparedStatement.setString(3, deliveryAddress.getDeliveryAddressCity());
            preparedStatement.setString(4, deliveryAddress.getDeliveryAddressState());
            preparedStatement.setString(5, deliveryAddress.getDeliveryAddressZip());

            preparedStatement.setInt(6, deliveryAddress.getDeliveryAddressID());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

}