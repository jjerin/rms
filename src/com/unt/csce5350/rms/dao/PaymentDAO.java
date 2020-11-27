package com.unt.csce5350.rms.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unt.csce5350.rms.updated.model.Payment;
import com.unt.csce5350.rms.utils.DBConnectionUtil;


/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table payments in the database.
 * 
 * @author Jerin Joseph
 *
 */
public class PaymentDAO {
    private static final String INSERT_PAYMENTS_SQL = "INSERT INTO payment" + "  (PaymentType, PaymentDate, PaymentTotalPaid, OrderID) VALUES " +
        " (?, ?, ?, ?);";

    private static final String SELECT_PAYMENT_BY_ID = "select * from payment where PaymentID =?";
    private static final String SELECT_ALL_PAYMENTS = "select * from payment";
    private static final String DELETE_PAYMENTS_SQL = "delete from payment where PaymentID = ?;";
    private static final String UPDATE_PAYMENTS_SQL = "update payment set PaymentType = ?, PaymentDate = ?, PaymentTotalPaid = ?, OrderID = ? where PaymentID = ?;";

    public PaymentDAO() {}

    public void insertPayment(Payment payment) throws SQLException {
        System.out.println(INSERT_PAYMENTS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DBConnectionUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PAYMENTS_SQL)) {
            preparedStatement.setString(1, payment.getPaymentType());
            preparedStatement.setDate(2, payment.getPaymentDate());
            preparedStatement.setBigDecimal(3, payment.getPaymentTotalPaid());
            preparedStatement.setInt(4, payment.getOrderID());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        	DBConnectionUtil.printSQLException(e);
        }
    }

    public Payment selectPayment(int id) {
        Payment payment = null;
        // Step 1: Establishing a Connection
        try (Connection connection = DBConnectionUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PAYMENT_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String paymentType = rs.getString("PaymentType");
                java.sql.Date paymentDate = rs.getDate("PaymentDate");
                BigDecimal paymentTotalPaid = rs.getBigDecimal("PaymentTotalPaid");
                int orderID = rs.getInt("OrderID");
                payment = new Payment(id, orderID, paymentDate, paymentTotalPaid, paymentType);
            }
        } catch (SQLException e) {
        	DBConnectionUtil.printSQLException(e);
        }
        return payment;
    }

    public List < Payment > selectAllPayments() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Payment > payments = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = DBConnectionUtil.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PAYMENTS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("PaymentID");
                String paymentType = rs.getString("PaymentType");
                java.sql.Date paymentDate = rs.getDate("PaymentDate");
                BigDecimal paymentTotalPaid = rs.getBigDecimal("PaymentTotalPaid");
                int orderID = rs.getInt("OrderID");

                payments.add(new Payment(id, orderID, paymentDate, paymentTotalPaid, paymentType));
            }
        } catch (SQLException e) {
        	DBConnectionUtil.printSQLException(e);
        }
        return payments;
    }

    public boolean deletePayment(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DBConnectionUtil.getConnection(); 
        		PreparedStatement statement = connection.prepareStatement(DELETE_PAYMENTS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updatePayment(Payment payment) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DBConnectionUtil.getConnection(); 
        		PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PAYMENTS_SQL);) {
            preparedStatement.setString(1, payment.getPaymentType());
            preparedStatement.setDate(2, payment.getPaymentDate());
            preparedStatement.setBigDecimal(3, payment.getPaymentTotalPaid());
            preparedStatement.setInt(4, payment.getOrderID());
            preparedStatement.setInt(5, payment.getPaymentID());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

}