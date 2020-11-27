package com.unt.csce5350.rms.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unt.csce5350.rms.updated.model.Menuitem;
import com.unt.csce5350.rms.updated.select.MenuItemSelect;
import com.unt.csce5350.rms.utils.DBConnectionUtil;


/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table menus in the database.
 * 
 * @author Jerin Joseph
 *
 */
public class MenuDAO {
    private static final String INSERT_MENUS_SQL = "INSERT INTO menuitem " + "  (MenuItemName, MenuItemDescription, MenuItemType, MenuItemPrice) VALUES " +
        " (?, ?, ?, ?);";

    private static final String SELECT_MENU_BY_ID = "select MenuItemID, MenuItemName, MenuItemDescription, MenuItemType, MenuItemPrice from menuitem where MenuItemID =?";
    private static final String SELECT_ALL_MENUS = "select * from menuitem";
    private static final String DELETE_MENUS_SQL = "delete from menuitem where MenuItemID = ?;";
    private static final String UPDATE_MENUS_SQL = "update menuitem set MenuItemName = ?, MenuItemDescription = ?, MenuItemType = ?, MenuItemPrice = ? where MenuItemID = ?;";
    private static final String SELECT_MENU_NAME_LIST = "select MenuItemID, MenuItemName from menuitem";

    public MenuDAO() {}
    
    public List<MenuItemSelect> selectMenuitemsNameList() {

    	List<MenuItemSelect> menuItemSelectList = new ArrayList<>();
    	menuItemSelectList.add(new MenuItemSelect());
       try (Connection connection = DBConnectionUtil.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MENU_NAME_LIST);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            

            while (rs.next()) {
                int id = rs.getInt("MenuitemID");
            	String menuItemFirstName        = rs.getString("MenuItemName");

            	menuItemSelectList.add(new MenuItemSelect(id, menuItemFirstName));
            }
        } catch (SQLException e) {
        	DBConnectionUtil.printSQLException(e);
        }
        return menuItemSelectList;
    }

    public void insertMenu(Menuitem menu) throws SQLException {
        System.out.println(INSERT_MENUS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DBConnectionUtil.getConnection(); 
        		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MENUS_SQL)) {
            preparedStatement.setString(1, menu.getMenuItemName());
            preparedStatement.setString(2, menu.getMenuItemDescription());
            preparedStatement.setString(3, menu.getMenuItemType());
            preparedStatement.setBigDecimal(4, menu.getMenuItemPrice());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        	DBConnectionUtil.printSQLException(e);
        }
    }

    public Menuitem selectMenu(int id) {
        Menuitem menu = null;
        // Step 1: Establishing a Connection
        try (Connection connection = DBConnectionUtil.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MENU_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String menuItemName = rs.getString("MenuItemName");
                String menuItemDescription = rs.getString("MenuItemDescription");
                String menuItemType = rs.getString("MenuItemType");
                BigDecimal menuItemPrice = rs.getBigDecimal("MenuItemPrice");
                
                menu = new Menuitem(id, menuItemDescription, menuItemName, menuItemPrice, menuItemType);
            }
        } catch (SQLException e) {
        	DBConnectionUtil.printSQLException(e);
        }
        return menu;
    }

    public List < Menuitem > selectAllMenus() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Menuitem > menus = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = DBConnectionUtil.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MENUS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("MenuItemID");
                String menuItemName = rs.getString("MenuItemName");
                String menuItemDescription = rs.getString("MenuItemDescription");
                String menuItemType = rs.getString("MenuItemType");
                BigDecimal menuItemPrice = rs.getBigDecimal("MenuItemPrice");
                
                menus.add(new Menuitem(id, menuItemDescription, menuItemName, menuItemPrice, menuItemType));
            }
        } catch (SQLException e) {
        	DBConnectionUtil.printSQLException(e);
        }
        return menus;
    }

    public boolean deleteMenu(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DBConnectionUtil.getConnection(); 
        		PreparedStatement statement = connection.prepareStatement(DELETE_MENUS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateMenu(Menuitem menu) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DBConnectionUtil.getConnection(); 
        		PreparedStatement statement = connection.prepareStatement(UPDATE_MENUS_SQL);) {
            statement.setString(1, menu.getMenuItemName());
            statement.setString(2, menu.getMenuItemDescription());
            statement.setString(3, menu.getMenuItemType());
            statement.setBigDecimal(3, menu.getMenuItemPrice());
            statement.setInt(4, menu.getMenuItemID());
            
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

}