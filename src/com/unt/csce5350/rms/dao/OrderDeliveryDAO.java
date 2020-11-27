package com.unt.csce5350.rms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unt.csce5350.rms.updated.model.Customer;
import com.unt.csce5350.rms.updated.select.CustomerSelect;
import com.unt.csce5350.rms.utils.DBConnectionUtil;


/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table customers in the database.
 * 
 * @author Jerin Joseph
 *
 */
public class OrderDeliveryDAO {

    private static final String INSERT_CUSTOMERS_SQL = "INSERT INTO customer" + "  (CustomerFirstName,CustomerLastName,CustomerStreet1,CustomerStreet2,CustomerCity,CustomerState,CustomerZip,CustomerPhone,CustomerEmail,CustomerOrders,CustomerCurrentDiscount) VALUES " +
        " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String SELECT_CUSTOMER_BY_ID = "select CustomerID,CustomerFirstName,CustomerLastName,CustomerStreet1,CustomerStreet2,CustomerCity,CustomerState,CustomerZip,CustomerPhone,CustomerEmail,CustomerOrders,CustomerCurrentDiscount from customer where CustomerID =?";
    private static final String SELECT_ALL_CUSTOMERS = "select * from customer";
    private static final String DELETE_CUSTOMERS_SQL = "delete from customer where CustomerID = ?;";
    private static final String UPDATE_CUSTOMERS_SQL = "update customer set CustomerFirstName = ?,CustomerLastName = ?,CustomerStreet1 = ?,CustomerStreet2 = ?,CustomerCity = ?,CustomerState = ?,CustomerZip = ?,CustomerPhone = ?,CustomerEmail = ?,CustomerOrders = ?,CustomerCurrentDiscount = ? where CustomerID = ?;";
    private static final String SELECT_CUSTOMER_NAME_LIST = "select CustomerID,CustomerFirstName,CustomerLastName from customer";
    private static final String SELECT_DELIVERY_ZIP_CODES = "select distinct DeliveryAreaZip from DeliveryAreaSelect;";
    private static final String SELECT_DELIVERY_ArREA_CODES = "select distinct DeliveryAreaZip from DeliveryAreaSelect;";

    public OrderDeliveryDAO() {}
    
    
    public List<String> getDeliveryZipCodes(){
    	List<String> deliveryZipCodes = new ArrayList<>();
    	deliveryZipCodes.add("");
       try (Connection connection = DBConnectionUtil.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DELIVERY_ZIP_CODES);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            

            while (rs.next()) {
                int zip = rs.getInt("DeliveryAreaZip");
            	deliveryZipCodes.add(zip+"");
            }
        } catch (SQLException e) {
        	DBConnectionUtil.printSQLException(e);
        }
        return deliveryZipCodes;
    }
    
    public List<CustomerSelect> selectCustomersNameList() {

    	List<CustomerSelect> customerSelectList = new ArrayList<>();
    	customerSelectList.add(new CustomerSelect());
       try (Connection connection = DBConnectionUtil.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_NAME_LIST);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            

            while (rs.next()) {
                int id = rs.getInt("CustomerID");
            	String customerFirstName        = rs.getString("CustomerFirstName");
            	String customerLastName         = rs.getString("CustomerLastName");

            	customerSelectList.add(new CustomerSelect(id, customerFirstName+" "+customerLastName));
            }
        } catch (SQLException e) {
        	DBConnectionUtil.printSQLException(e);
        }
        return customerSelectList;
    }



    public void insertCustomer(Customer customer) throws SQLException {
        System.out.println(INSERT_CUSTOMERS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DBConnectionUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMERS_SQL)) {
            preparedStatement.setString(1, customer.getCustomerFirstName());
            preparedStatement.setString(2, customer.getCustomerLastName());
            preparedStatement.setString(3, customer.getCustomerStreet1());
            preparedStatement.setString(4, customer.getCustomerStreet2());
            preparedStatement.setString(5, customer.getCustomerCity());
            preparedStatement.setString(6, customer.getCustomerState());
            preparedStatement.setString(7, customer.getCustomerZip());
            preparedStatement.setString(8, customer.getCustomerPhone());
            preparedStatement.setString(9, customer.getCustomerEmail());
            preparedStatement.setInt(10, customer.getCustomerOrders());
            preparedStatement.setInt(11, customer.getCustomerCurrentDiscount());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            DBConnectionUtil.printSQLException(e);
        }
    }
    
    public void insertCustomer(Customer customer, Connection connection) throws SQLException {
        // System.out.println(INSERT_CUSTOMERS_SQL);
        // try-with-resource statement will auto close the connection.
        try  {
        	PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMERS_SQL);
            preparedStatement.setString(1, customer.getCustomerFirstName());
            preparedStatement.setString(2, customer.getCustomerLastName());
            preparedStatement.setString(3, customer.getCustomerStreet1());
            preparedStatement.setString(4, customer.getCustomerStreet2());
            preparedStatement.setString(5, customer.getCustomerCity());
            preparedStatement.setString(6, customer.getCustomerState());
            preparedStatement.setString(7, customer.getCustomerZip());
            preparedStatement.setString(8, customer.getCustomerPhone());
            preparedStatement.setString(9, customer.getCustomerEmail());
            preparedStatement.setInt(10, customer.getCustomerOrders());
            preparedStatement.setInt(11, customer.getCustomerCurrentDiscount());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            DBConnectionUtil.printSQLException(e);
        }
    }

    public Customer selectCustomer(int id) {
        Customer customer = null;
        // Step 1: Establishing a Connection
        try (Connection connection = DBConnectionUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	
            	int customerID = rs.getInt("CustomerID");
            	String customerFirstName = rs.getString("CustomerFirstName");
            	String customerLastName = rs.getString("CustomerLastName");
            	String customerStreet1 = rs.getString("CustomerStreet1");
            	String customerStreet2 = rs.getString("CustomerStreet2");
            	String customerCity = rs.getString("CustomerCity");
            	String customerState = rs.getString("CustomerState");
            	String customerZip = rs.getString("CustomerZip");
            	String customerPhone = rs.getString("CustomerPhone");
            	String customerEmail = rs.getString("CustomerEmail");
            	int customerOrders = rs.getInt("CustomerOrders");
            	int customerCurrentDiscount = rs.getInt("CustomerCurrentDiscount");
            	
            	customer = new Customer(customerID, customerCity, customerCurrentDiscount, customerEmail,
            			customerFirstName, customerLastName, customerOrders, customerPhone,
            			customerState, customerStreet1, customerStreet2, customerZip);
            }
        } catch (SQLException e) {
            DBConnectionUtil.printSQLException(e);
        }
        return customer;
    }

    public List < Customer > selectAllCustomers() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Customer > customers = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = DBConnectionUtil.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int customerID = rs.getInt("CustomerID");
            	String customerFirstName = rs.getString("CustomerFirstName");
            	String customerLastName = rs.getString("CustomerLastName");
            	String customerStreet1 = rs.getString("CustomerStreet1");
            	String customerStreet2 = rs.getString("CustomerStreet2");
            	String customerCity = rs.getString("CustomerCity");
            	String customerState = rs.getString("CustomerState");
            	String customerZip = rs.getString("CustomerZip");
            	String customerPhone = rs.getString("CustomerPhone");
            	String customerEmail = rs.getString("CustomerEmail");
            	int customerOrders = rs.getInt("CustomerOrders");
            	int customerCurrentDiscount = rs.getInt("CustomerCurrentDiscount");
            	
            	customers.add(new Customer(customerID, customerCity, customerCurrentDiscount, customerEmail,
            			customerFirstName, customerLastName, customerOrders, customerPhone,
            			customerState, customerStreet1, customerStreet2, customerZip));

            	
            }
        } catch (SQLException e) {
            DBConnectionUtil.printSQLException(e);
        }
        return customers;
    }

    public boolean deleteCustomer(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DBConnectionUtil.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMERS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateCustomer(Customer customer) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DBConnectionUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMERS_SQL);) {
        	
            preparedStatement.setString(1, customer.getCustomerFirstName());
            preparedStatement.setString(2, customer.getCustomerLastName());
            preparedStatement.setString(3, customer.getCustomerStreet1());
            preparedStatement.setString(4, customer.getCustomerStreet2());
            preparedStatement.setString(5, customer.getCustomerCity());
            preparedStatement.setString(6, customer.getCustomerState());
            preparedStatement.setString(7, customer.getCustomerZip());
            preparedStatement.setString(8, customer.getCustomerPhone());
            preparedStatement.setString(9, customer.getCustomerEmail());
            preparedStatement.setInt(10, customer.getCustomerOrders());
            preparedStatement.setInt(11, customer.getCustomerCurrentDiscount());

            preparedStatement.setInt(12, customer.getCustomerID());

            
            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }


}