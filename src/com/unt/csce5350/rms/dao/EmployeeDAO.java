package com.unt.csce5350.rms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unt.csce5350.rms.updated.model.Employee;
import com.unt.csce5350.rms.updated.select.EmployeeSelect;
import com.unt.csce5350.rms.utils.DBConnectionUtil;


/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table employees in the database.
 * 
 * @author Jerin Joseph
 *
 */
public class EmployeeDAO {
    private static final String INSERT_EMPLOYEES_SQL = "INSERT INTO employee" + "  (EmployeeFirstName, EmployeeLastName, EmployeeStreet1, EmployeeStreet2, EmployeeCity, EmployeeState, EmployeeZip, EmployeePhone) VALUES " +
        " (?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String SELECT_EMPLOYEE_BY_ID = "select EmployeeID, EmployeeFirstName, EmployeeLastName, EmployeeStreet1, EmployeeStreet2, EmployeeCity, EmployeeState, EmployeeZip, EmployeePhone  from employee where EmployeeID =?";
    private static final String SELECT_ALL_EMPLOYEES = "select * from employee";
    private static final String DELETE_EMPLOYEES_SQL = "delete from employee where EmployeeID = ?;";
    private static final String UPDATE_EMPLOYEES_SQL = "update employee set EmployeeFirstName = ?, EmployeeLastName = ?, EmployeeStreet1 = ?, EmployeeStreet2 = ?, EmployeeCity = ?, EmployeeState = ?, EmployeeZip = ?, EmployeePhone = ? where EmployeeID = ?;";
    private static final String SELECT_EMPLOYEE_NAME_LIST = "select EmployeeID, EmployeeFirstName, EmployeeLastName from employee";

    public EmployeeDAO() {}
    
    public List<EmployeeSelect> selectEmployeesNameList() {

    	List<EmployeeSelect> employeeSelectList = new ArrayList<>();
    	employeeSelectList.add(new EmployeeSelect());
       try (Connection connection = DBConnectionUtil.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_NAME_LIST);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            

            while (rs.next()) {
                int id = rs.getInt("EmployeeID");
            	String employeeFirstName        = rs.getString("EmployeeFirstName");
            	String employeeLastName         = rs.getString("EmployeeLastName");

            	employeeSelectList.add(new EmployeeSelect(id, employeeFirstName+" "+employeeLastName));
            }
        } catch (SQLException e) {
        	DBConnectionUtil.printSQLException(e);
        }
        return employeeSelectList;
    }

    public void insertEmployee(Employee employee) throws SQLException {
        System.out.println(INSERT_EMPLOYEES_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DBConnectionUtil.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEES_SQL)) {
            preparedStatement.setString(1, employee.getEmployeeFirstName());
            preparedStatement.setString(2, employee.getEmployeeLastName());
            preparedStatement.setString(3, employee.getEmployeeStreet1());
            preparedStatement.setString(4, employee.getEmployeeStreet2());
            preparedStatement.setString(5, employee.getEmployeeCity());
            preparedStatement.setString(6, employee.getEmployeeState());
            preparedStatement.setString(7, employee.getEmployeeZip());
            preparedStatement.setString(8, employee.getEmployeePhone());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        	DBConnectionUtil.printSQLException(e);
        }
    }

    public Employee selectEmployee(int id) {
        Employee employee = null;
        // Step 1: Establishing a Connection
        try (Connection connection = DBConnectionUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	String employeeFirstName        = rs.getString("EmployeeFirstName");
            	String employeeLastName         = rs.getString("EmployeeLastName");
            	String employeeStreet1          = rs.getString("EmployeeStreet1");
            	String employeeStreet2          = rs.getString("EmployeeStreet2");
            	String employeeCity             = rs.getString("EmployeeCity");
            	String employeeState            = rs.getString("EmployeeState");
            	String employeeZip              = rs.getString("EmployeeZip");
            	String employeePhone            = rs.getString("EmployeePhone");
            	
            	employee = new Employee(id, employeeCity, employeeFirstName, employeeLastName,
            			employeePhone, employeeState, employeeStreet1, employeeStreet2, employeeZip);
            			
            }
        } catch (SQLException e) {
        	DBConnectionUtil.printSQLException(e);
        }
        return employee;
    }

    public List < Employee > selectAllEmployees() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Employee > employees = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = DBConnectionUtil.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEES);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("EmployeeID");
            	String employeeFirstName        = rs.getString("EmployeeFirstName");
            	String employeeLastName         = rs.getString("EmployeeLastName");
            	String employeeStreet1          = rs.getString("EmployeeStreet1");
            	String employeeStreet2          = rs.getString("EmployeeStreet2");
            	String employeeCity             = rs.getString("EmployeeCity");
            	String employeeState            = rs.getString("EmployeeState");
            	String employeeZip              = rs.getString("EmployeeZip");
            	String employeePhone            = rs.getString("EmployeePhone");
                employees.add(new Employee(id, employeeCity, employeeFirstName, employeeLastName,
            			employeePhone, employeeState, employeeStreet1, employeeStreet2, employeeZip));
            }
        } catch (SQLException e) {
        	DBConnectionUtil.printSQLException(e);
        }
        return employees;
    }

    public boolean deleteEmployee(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DBConnectionUtil.getConnection(); 
        		PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEES_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateEmployee(Employee employee) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DBConnectionUtil.getConnection(); 
        		PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEES_SQL);) {
            preparedStatement.setString(1, employee.getEmployeeFirstName());
            preparedStatement.setString(2, employee.getEmployeeLastName());
            preparedStatement.setString(3, employee.getEmployeeStreet1());
            preparedStatement.setString(4, employee.getEmployeeStreet2());
            preparedStatement.setString(5, employee.getEmployeeCity());
            preparedStatement.setString(6, employee.getEmployeeState());
            preparedStatement.setString(7, employee.getEmployeeZip());
            preparedStatement.setString(8, employee.getEmployeePhone());

            preparedStatement.setInt(9, employee.getEmployeeID());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

}