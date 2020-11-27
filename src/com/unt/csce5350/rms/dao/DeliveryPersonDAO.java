package com.unt.csce5350.rms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unt.csce5350.rms.updated.model.Deliveryperson;
import com.unt.csce5350.rms.updated.select.DeliveryPersonSelect;
import com.unt.csce5350.rms.utils.DBConnectionUtil;


/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table deliveryPersons in the database.
 * 
 * @author Jerin Joseph
 *
 */
public class DeliveryPersonDAO {
    private static final String INSERT_DELIVERYPERSON_SQL = "INSERT INTO deliveryPerson" + "  ( DeliveryPersonFirstName, DeliveryPersonLastName, DeliveryPersonStreet1, DeliveryPersonStreet2, DeliveryPersonCity, DeliveryPersonState, DeliveryPersonZip, DeliveryPersonPhone, DeliveryAreaID) VALUES " +
        " (?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String SELECT_DELIVERYPERSON_BY_ID = "select * from deliveryPerson where DeliveryPersonID =?";
    private static final String SELECT_ALL_DELIVERYPERSON = "select * from deliveryPerson";
    private static final String DELETE_DELIVERYPERSON_SQL = "delete from deliveryPerson where DeliveryPersonID = ?;";
    private static final String UPDATE_DELIVERYPERSON_SQL = "update deliveryPerson set DeliveryPersonFirstName = ?, DeliveryPersonLastName = ?, DeliveryPersonStreet1 = ?, DeliveryPersonStreet2 = ?, DeliveryPersonCity = ?, DeliveryPersonState = ?, DeliveryPersonZip = ?, DeliveryPersonPhone = ?, DeliveryAreaID = ? where DeliveryPersonID = ?;";
    private static final String SELECT_DELIVERY_PERSON_NAME_LIST = "select DeliveryPersonID, DeliveryPersonFirstName, DeliveryPersonLastName from deliveryPerson";
    private static final String SELECT_DELIVERY_PERSON_NAME_LIST_BY_ID = "select DeliveryPersonID, DeliveryPersonFirstName, DeliveryPersonLastName from deliveryPerson where DeliveryAreaID = ? ";

    public DeliveryPersonDAO() {}
    
    public List<DeliveryPersonSelect> selectDeliveryPersonsNameList() {

    	List<DeliveryPersonSelect> deliveryPersonSelectList = new ArrayList<>();
    	deliveryPersonSelectList.add(new DeliveryPersonSelect());
       try (Connection connection = DBConnectionUtil.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DELIVERY_PERSON_NAME_LIST);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            

            while (rs.next()) {
                int id = rs.getInt("DeliveryPersonID");
            	String deliveryPersonFirstName        = rs.getString("DeliveryPersonFirstName");
            	String deliveryPersonLastName         = rs.getString("DeliveryPersonLastName");

            	deliveryPersonSelectList.add(new DeliveryPersonSelect(id, deliveryPersonFirstName+" "+deliveryPersonLastName));
            }
        } catch (SQLException e) {
        	DBConnectionUtil.printSQLException(e);
        }
        return deliveryPersonSelectList;
    }
    
    public List<DeliveryPersonSelect> selectDeliveryPersonsNameList(int deliveryAreaID) {

    	List<DeliveryPersonSelect> deliveryPersonSelectList = new ArrayList<>();
    	deliveryPersonSelectList.add(new DeliveryPersonSelect());
       try (Connection connection = DBConnectionUtil.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DELIVERY_PERSON_NAME_LIST_BY_ID);) {
           preparedStatement.setInt(1, deliveryAreaID);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            

            while (rs.next()) {
                int id = rs.getInt("DeliveryPersonID");
            	String deliveryPersonFirstName        = rs.getString("DeliveryPersonFirstName");
            	String deliveryPersonLastName         = rs.getString("DeliveryPersonLastName");

            	deliveryPersonSelectList.add(new DeliveryPersonSelect(id, deliveryPersonFirstName+" "+deliveryPersonLastName));
            }
        } catch (SQLException e) {
        	DBConnectionUtil.printSQLException(e);
        }
        return deliveryPersonSelectList;
    }

    public void insertDeliveryPerson(Deliveryperson deliveryPerson) throws SQLException {
        System.out.println(INSERT_DELIVERYPERSON_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DBConnectionUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DELIVERYPERSON_SQL)) {
            preparedStatement.setString(1, deliveryPerson.getDeliveryPersonFirstName());
            preparedStatement.setString(2, deliveryPerson.getDeliveryPersonLastName());
            preparedStatement.setString(3, deliveryPerson.getDeliveryPersonStreet1());
            preparedStatement.setString(4, deliveryPerson.getDeliveryPersonStreet2());
            preparedStatement.setString(5, deliveryPerson.getDeliveryPersonCity());
            preparedStatement.setString(6, deliveryPerson.getDeliveryPersonState());
            preparedStatement.setString(7, deliveryPerson.getDeliveryPersonZip());
            preparedStatement.setString(8, deliveryPerson.getDeliveryPersonPhone());
            preparedStatement.setInt(9, deliveryPerson.getDeliveryAreaID());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        	DBConnectionUtil.printSQLException(e);
        }
    }

    public Deliveryperson selectDeliveryPerson(int id) {
        Deliveryperson deliveryPerson = null;
        // Step 1: Establishing a Connection
        try (Connection connection = DBConnectionUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DELIVERYPERSON_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	String deliveryPersonFirstName = rs.getString("DeliveryPersonFirstName");
            	String deliveryPersonLastName = rs.getString("DeliveryPersonLastName");
            	String deliveryPersonStreet1 = rs.getString("DeliveryPersonStreet1");
            	String deliveryPersonStreet2 = rs.getString("DeliveryPersonStreet2");
            	String deliveryPersonCity = rs.getString("DeliveryPersonCity");
            	String deliveryPersonState = rs.getString("DeliveryPersonState");
            	String deliveryPersonZip = rs.getString("DeliveryPersonZip");
            	String deliveryPersonPhone = rs.getString("DeliveryPersonPhone");
            	int deliveryAreaID = rs.getInt("DeliveryAreaID");
            	
            	deliveryPerson = new Deliveryperson(id, deliveryPersonCity, deliveryPersonFirstName, deliveryPersonLastName,
            			deliveryPersonPhone, deliveryPersonState, deliveryPersonStreet1,
            			deliveryPersonStreet2, deliveryPersonZip, deliveryAreaID);
            }
        } catch (SQLException e) {
        	DBConnectionUtil.printSQLException(e);
        }
        return deliveryPerson;
    }

    public List < Deliveryperson > selectAllDeliveryPersons() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Deliveryperson > deliveryPersons = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = DBConnectionUtil.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DELIVERYPERSON);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int deliveryPersonID = rs.getInt("DeliveryPersonID");
            	String deliveryPersonFirstName = rs.getString("DeliveryPersonFirstName");
            	String deliveryPersonLastName = rs.getString("DeliveryPersonLastName");
            	String deliveryPersonStreet1 = rs.getString("DeliveryPersonStreet1");
            	String deliveryPersonStreet2 = rs.getString("DeliveryPersonStreet2");
            	String deliveryPersonCity = rs.getString("DeliveryPersonCity");
            	String deliveryPersonState = rs.getString("DeliveryPersonState");
            	String deliveryPersonZip = rs.getString("DeliveryPersonZip");
            	String deliveryPersonPhone = rs.getString("DeliveryPersonPhone");
            	int deliveryAreaID = rs.getInt("DeliveryAreaID");                
            	
            	deliveryPersons.add(new Deliveryperson(deliveryPersonID, deliveryPersonCity, deliveryPersonFirstName, deliveryPersonLastName,
            			deliveryPersonPhone, deliveryPersonState, deliveryPersonStreet1,
            			deliveryPersonStreet2, deliveryPersonZip, deliveryAreaID));
            }
        } catch (SQLException e) {
        	DBConnectionUtil.printSQLException(e);
        }
        return deliveryPersons;
    }

    public boolean deleteDeliveryPerson(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DBConnectionUtil.getConnection(); 
        		PreparedStatement statement = connection.prepareStatement(DELETE_DELIVERYPERSON_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateDeliveryPerson(Deliveryperson deliveryPerson) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DBConnectionUtil.getConnection(); 
        		PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DELIVERYPERSON_SQL);) {
            preparedStatement.setString(1, deliveryPerson.getDeliveryPersonFirstName());
            preparedStatement.setString(2, deliveryPerson.getDeliveryPersonLastName());
            preparedStatement.setString(3, deliveryPerson.getDeliveryPersonStreet1());
            preparedStatement.setString(4, deliveryPerson.getDeliveryPersonStreet2());
            preparedStatement.setString(5, deliveryPerson.getDeliveryPersonCity());
            preparedStatement.setString(6, deliveryPerson.getDeliveryPersonState());
            preparedStatement.setString(7, deliveryPerson.getDeliveryPersonZip());
            preparedStatement.setString(8, deliveryPerson.getDeliveryPersonPhone());
            preparedStatement.setInt(9, deliveryPerson.getDeliveryAreaID());

            preparedStatement.setInt(10, deliveryPerson.getDeliveryPersonID());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

}