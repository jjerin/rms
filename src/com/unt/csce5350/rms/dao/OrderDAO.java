package com.unt.csce5350.rms.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.unt.csce5350.rms.updated.model.Customer;
import com.unt.csce5350.rms.updated.model.Order;
import com.unt.csce5350.rms.updated.model.Orderdetail;
import com.unt.csce5350.rms.utils.AppUtils;
import com.unt.csce5350.rms.utils.DBConnectionUtil;
import com.unt.csce5350.rms.utils.RMSConstants;


/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table orders in the database.
 * 
 * @author Jerin Joseph
 *
 */
public class OrderDAO {
    private static final String INSERT_ORDERS_SQL = "INSERT INTO "+RMSConstants.dbSchema+".order" + "  (EmployeeID, DeliveryPersonID, CustomerID, OrderType, OrderTotalCost, DeliveryAddressID, OrderDateTime) VALUES " +
        " (?, ?, ?, ?, ?, ?, ?);";

    private static final String SELECT_ORDER_BY_ID = "select * from "+RMSConstants.dbSchema+".order where OrderID =?";
    private static final String SELECT_ALL_ORDERS = "select * from "+RMSConstants.dbSchema+".order";
    private static final String DELETE_ORDERS_SQL = "delete from "+RMSConstants.dbSchema+".order where OrderID = ?;";
    private static final String UPDATE_ORDERS_SQL = "update "+RMSConstants.dbSchema+".order set EmployeeID = ?, DeliveryPersonID = ?, CustomerID = ?, OrderType = ?, OrderTotalCost = ?, DeliveryAddressID = ?, OrderDateTime = ? where OrderID = ?;";

    
    private static final String INSERT_ORDER_DETAIL_SQL = "INSERT INTO "+RMSConstants.dbSchema+".orderdetails" + "  (OrderID, MenuItemID, MenuItemQuantity, OrderDetailPrice, OrderDetailsComments) VALUES " +
            " (?, ?, ?, ?, ?);";

    private static final String SELECT_ORDER_DETAIL_BY_ID = "select * from "+RMSConstants.dbSchema+".orderdetails where OrderID =?";
    private static final String UPDATE_ORDER_DETAIL_SQL = "update "+RMSConstants.dbSchema+".orderdetails set MenuItemQuantity = ?, OrderDetailPrice = ?, OrderDetailsComments = ? where OrderID = ? and MenuItemID = ?;";
    private static final String DELETE_ORDER_DETAIL_SQL = "delete from "+RMSConstants.dbSchema+".orderdetails where OrderID = ?;";

    public OrderDAO() {}

    public int insertOrder(Order order) throws SQLException {
        System.out.println(INSERT_ORDERS_SQL);
        // try-with-resource statement will auto close the connection.
        int orderId = 0;
       try (Connection connection = DBConnectionUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDERS_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, order.getEmployeeID());
            if(order.getDeliveryPersonID() ==0) {
            	preparedStatement.setNull(2, Types.NUMERIC);
            }else {
            	preparedStatement.setInt(2, order.getDeliveryPersonID());
            }
            preparedStatement.setInt(3, order.getCustomerID());
            preparedStatement.setString(4, order.getOrderType());
            preparedStatement.setBigDecimal(5, order.getOrderTotalCost());
            if(order.getDeliveryAddressID() ==0) {
            	preparedStatement.setNull(6, Types.NUMERIC);
            }else {
                preparedStatement.setInt(6, order.getDeliveryAddressID());
            }
            preparedStatement.setDate(7, order.getOrderDateTime());
            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();
            
            System.out.println("Order id 1: "+orderId);
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
                orderId=rs.getInt(1);
                System.out.println("Order id 2: "+orderId);
            }
            System.out.println("Order id 3: "+orderId);
            
    		List<Orderdetail> orderDetailList = order.getOrderDetailList();
    		if(!AppUtils.isEmpty(orderDetailList)) {
    			insertOrderDetails(orderId, orderDetailList);
    		}

        } catch (SQLException e) {
        	DBConnectionUtil.printSQLException(e);
        }
       return orderId;
    }

    public Order selectOrder(int id) {
        Order order = null;
        // Step 1: Establishing a Connection
        try (Connection connection = DBConnectionUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int employeeID = rs.getInt("EmployeeID");
            	int deliveryPersonID = rs.getInt("DeliveryPersonID");
            	int customerID = rs.getInt("CustomerID");
            	String orderType = rs.getString("OrderType");
            	BigDecimal orderTotalCost = rs.getBigDecimal("OrderTotalCost");
            	int deliveryAddressID = rs.getInt("DeliveryAddressID");
            	Date orderDateTime = rs.getDate("OrderDateTime");

                order = new Order(id, orderDateTime, orderTotalCost, orderType, employeeID,
            			deliveryPersonID, customerID, deliveryAddressID);
            }
            
			List<Orderdetail> orderDetailList = new ArrayList<>();
           
            PreparedStatement preparedStatement1 = connection.prepareStatement(SELECT_ORDER_DETAIL_BY_ID);
            preparedStatement1.setInt(1, id);
            ResultSet rs1 = preparedStatement1.executeQuery();
            while (rs1.next()) {
            	
            	int orderID = rs1.getInt("OrderID");
            	int menuItemId = rs1.getInt("MenuItemID");
            	int menuItemQuantity = rs1.getInt("MenuItemQuantity");
            	BigDecimal orderDetailPrice = rs1.getBigDecimal("OrderDetailPrice");
            	String orderDetailsComments = rs1.getString("OrderDetailsComments");
            	
            	Orderdetail od = new Orderdetail(menuItemId, menuItemQuantity, orderDetailPrice, orderDetailsComments);
            	orderDetailList.add(od);
            	
            }
            
            order.setOrderDetailList(orderDetailList);
            
        } catch (SQLException e) {
        	DBConnectionUtil.printSQLException(e);
        }
        return order;
    }

    public List < Order > selectAllOrders() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Order > orders = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = DBConnectionUtil.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("OrderID");
            	int employeeID = rs.getInt("EmployeeID");
            	int deliveryPersonID = rs.getInt("DeliveryPersonID");
            	int customerID = rs.getInt("CustomerID");
            	String orderType = rs.getString("OrderType");
            	BigDecimal orderTotalCost = rs.getBigDecimal("OrderTotalCost");
            	int deliveryAddressID = rs.getInt("DeliveryAddressID");
            	Date orderDateTime = rs.getDate("OrderDateTime");
            	
    			List<Orderdetail> orderDetailList = new ArrayList<>();
    	           
                PreparedStatement preparedStatement1 = connection.prepareStatement(SELECT_ORDER_DETAIL_BY_ID);
                preparedStatement1.setInt(1, id);
                ResultSet rs1 = preparedStatement1.executeQuery();
                while (rs1.next()) {
                	
                	int orderID = rs1.getInt("OrderID");
                	int menuItemId = rs1.getInt("MenuItemID");
                	int menuItemQuantity = rs1.getInt("MenuItemQuantity");
                	BigDecimal orderDetailPrice = rs1.getBigDecimal("OrderDetailPrice");
                	String orderDetailsComments = rs1.getString("OrderDetailsComments");
                	
                	Orderdetail od = new Orderdetail(menuItemId, menuItemQuantity, orderDetailPrice, orderDetailsComments);
                	orderDetailList.add(od);
                	
                }
                
                Order order = new Order(id, orderDateTime, orderTotalCost, orderType, employeeID,
            			deliveryPersonID, customerID, deliveryAddressID);
                order.setOrderDetailList(orderDetailList);
            	
            	orders.add(order);
            }
        } catch (SQLException e) {
        	DBConnectionUtil.printSQLException(e);
        }
        return orders;
    }

    public boolean deleteOrder(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DBConnectionUtil.getConnection(); 
        		PreparedStatement statement = connection.prepareStatement(DELETE_ORDERS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateOrder(Order order) throws SQLException {
    	
    	System.out.println("updateOrder: order: "+order);
        boolean rowUpdated;
        try (Connection connection = DBConnectionUtil.getConnection(); 
        		PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDERS_SQL);) {
            preparedStatement.setInt(1, order.getEmployeeID());
            if(order.getDeliveryPersonID() ==0) {
            	preparedStatement.setNull(2, Types.NUMERIC);
            }else {
            	preparedStatement.setInt(2, order.getDeliveryPersonID());
            }       
            preparedStatement.setInt(3, order.getCustomerID());
            preparedStatement.setString(4, order.getOrderType());
            preparedStatement.setBigDecimal(5, order.getOrderTotalCost());
            if(order.getDeliveryAddressID() ==0) {
            	preparedStatement.setNull(6, Types.NUMERIC);
            }else {
                preparedStatement.setInt(6, order.getDeliveryAddressID());
            }
            preparedStatement.setDate(7, order.getOrderDateTime());
            
            preparedStatement.setInt(8, order.getOrderID());


            rowUpdated = preparedStatement.executeUpdate() > 0;
            
            // Insert Order Detail
			int orderId = order.getOrderID();
    		List<Orderdetail> orderDetailList = order.getOrderDetailList();

    		
    		deleteOrderDetail(orderId);
    		if(!AppUtils.isEmpty(orderDetailList)) {
    			insertOrderDetails(orderId, orderDetailList);
    		}

			/*
			int orderId = order.getOrderID();
			List<Orderdetail> orderDetailList = order.getOrderDetailList();
			
			for(Orderdetail od: orderDetailList) {
				System.out.println("Updating order detail for the order");
					
				PreparedStatement odPreparedStatement = connection.prepareStatement(UPDATE_ORDER_DETAIL_SQL);
				    odPreparedStatement.setInt(1, od.getMenuItemId());
				    odPreparedStatement.setInt(2, od.getMenuItemQuantity());
				    odPreparedStatement.setBigDecimal(3, od.getOrderDetailPrice());
				    odPreparedStatement.setString(4, od.getOrderDetailsComments());
			    	odPreparedStatement.setInt(5, orderId);
				    System.out.println(odPreparedStatement);
				    odPreparedStatement.executeUpdate();
			}*/
        }
        return rowUpdated;
    }
    
    
    public boolean deleteOrderDetail(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DBConnectionUtil.getConnection(); 
        		PreparedStatement statement = connection.prepareStatement(DELETE_ORDER_DETAIL_SQL);) {
            statement.setInt(1, id);
        	System.out.println(statement);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    
    public void insertOrderDetails(int orderId, List<Orderdetail> orderDetailList) throws SQLException {
    	
		for(Orderdetail od: orderDetailList) {
			System.out.println("Inserting order detail for the order");
	        System.out.println(INSERT_ORDER_DETAIL_SQL);
	        // try-with-resource statement will auto close the connection.
	        try (Connection connection = DBConnectionUtil.getConnection(); PreparedStatement odPreparedStatement = connection.prepareStatement(INSERT_ORDER_DETAIL_SQL)) {
		    	odPreparedStatement.setInt(1, orderId);
			    odPreparedStatement.setInt(2, od.getMenuItemId());
			    odPreparedStatement.setInt(3, od.getMenuItemQuantity());
			    odPreparedStatement.setBigDecimal(4, od.getOrderDetailPrice());
			    odPreparedStatement.setString(5, od.getOrderDetailsComments());
			    System.out.println(odPreparedStatement);
			    odPreparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            DBConnectionUtil.printSQLException(e);
	        }
		}

    }
    
    public void updateDeliveryDetails(int orderId, int deliveryPersonId, int deliveryAddressId) {
    	try {
			Order order = selectOrder(orderId);
			order.setDeliveryPersonID(deliveryPersonId);
			order.setDeliveryAddressID(deliveryAddressId);
			
			updateOrder(order);
		} catch (SQLException e) {
            DBConnectionUtil.printSQLException(e);
		}
    }

}