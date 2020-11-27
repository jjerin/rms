package com.unt.csce5350.rms.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.unt.csce5350.rms.dao.CustomerDAO;
import com.unt.csce5350.rms.dao.DeliveryAddressDAO;
import com.unt.csce5350.rms.dao.DeliveryAreaDAO;
import com.unt.csce5350.rms.dao.DeliveryPersonDAO;
import com.unt.csce5350.rms.dao.EmployeeDAO;
import com.unt.csce5350.rms.dao.MenuDAO;
import com.unt.csce5350.rms.dao.OrderDeliveryDAO;
import com.unt.csce5350.rms.updated.model.Deliveryaddress;
import com.unt.csce5350.rms.updated.model.Deliveryarea;
import com.unt.csce5350.rms.updated.select.CustomerSelect;
import com.unt.csce5350.rms.updated.select.DeliveryAreaSelect;
import com.unt.csce5350.rms.updated.select.DeliveryPersonSelect;
import com.unt.csce5350.rms.updated.select.EmployeeSelect;
import com.unt.csce5350.rms.updated.select.MenuItemSelect;
import com.unt.csce5350.rms.updated.select.OrderTypeSelect;
import com.unt.csce5350.rms.updated.select.ZipCodeSelect;

public class SelectUtils {
	
	
    
    public static List<EmployeeSelect> getEmployeeSelectList() {
    	
    	EmployeeDAO employeeDao = new EmployeeDAO();
    	return employeeDao.selectEmployeesNameList();
    	
    	
		/*EmployeeSelect es0 = new EmployeeSelect();
		EmployeeSelect es1 = new EmployeeSelect(1, "Jerin Joseph");
		EmployeeSelect es2 = new EmployeeSelect(2, "Jancy Jerin");
		EmployeeSelect es3 = new EmployeeSelect(3, "Janice Jerin");
		EmployeeSelect es4 = new EmployeeSelect(4, "Jonathan Jerin");
		
		List<EmployeeSelect> employeeSelectList = new ArrayList<>();
		employeeSelectList.add(es0);
		employeeSelectList.add(es1);
		employeeSelectList.add(es2);
		employeeSelectList.add(es3);
		employeeSelectList.add(es4);
		
		return employeeSelectList;*/
    }
    
    
    public static List<CustomerSelect> getCustomerSelectList() {
    	
    	CustomerDAO customerDao = new CustomerDAO();
    	return customerDao.selectCustomersNameList();
    }
    public static List<DeliveryPersonSelect> getDeliveryPersonSelectList() {
    	
    	DeliveryPersonDAO deliveryPersonDao = new DeliveryPersonDAO();
    	return deliveryPersonDao.selectDeliveryPersonsNameList();
    }
    
    public static List<DeliveryPersonSelect> getDeliveryPersonSelectList(int deliveryAreaId) {
    	
    	DeliveryPersonDAO deliveryPersonDao = new DeliveryPersonDAO();
    	return deliveryPersonDao.selectDeliveryPersonsNameList(deliveryAreaId);
    }
    
    public static List<OrderTypeSelect> getOrderTypeSelectList() {
    	OrderTypeSelect ot1 = new OrderTypeSelect("Dine-In", "Dine-In");
    	OrderTypeSelect ot2 = new OrderTypeSelect("Delivery", "Delivery");
    	
		List<OrderTypeSelect> orderTypeSelectList = new ArrayList<>();
		orderTypeSelectList.add(ot1);
		orderTypeSelectList.add(ot2);
		
		return orderTypeSelectList;
    }
    
    public static List<MenuItemSelect> getMenuItemSelectList() {
    	
    	MenuDAO menuItemDao = new MenuDAO();
    	return menuItemDao.selectMenuitemsNameList();
    }
    
    public static List<ZipCodeSelect> getZipCodeSelectList() {
    	
    	OrderDeliveryDAO orderDeliveryDAO = new OrderDeliveryDAO();
    	List<String> deliveryZipCodes = orderDeliveryDAO.getDeliveryZipCodes();
    	
    	List<ZipCodeSelect> zipCodeSelectList = new ArrayList<>();
    	for(String zipCode: deliveryZipCodes) {
    		ZipCodeSelect zipCodeSelect = new ZipCodeSelect(zipCode, zipCode);
    		zipCodeSelectList.add(zipCodeSelect);
    	}

    	return zipCodeSelectList;
    	
    	

    }
    
    public static List<DeliveryAreaSelect> getDeliveryAreaSelectList() {
    	
    	DeliveryAreaDAO deliveryAreaDAO = new DeliveryAreaDAO();
    	List<Deliveryarea> deliveryAreaList = deliveryAreaDAO.selectAllDeliveryAreas();
    	
    	List<DeliveryAreaSelect> deliveryAreaSelectList = new ArrayList<>();
    	for(Deliveryarea deliveryArea: deliveryAreaList) {
    		DeliveryAreaSelect zipCodeSelect = new DeliveryAreaSelect(deliveryArea.getDeliveryAreaID(), deliveryArea.getDeliveryAreaName()+" - "+deliveryArea.getDeliveryAreaZip());
    		deliveryAreaSelectList.add(zipCodeSelect);
    	}

    	return deliveryAreaSelectList;
    }
    
    
    public static Map<Integer, String> getDeliveryAddressMap() {
    	
    	DeliveryAddressDAO deliveryAddressDAO = new DeliveryAddressDAO();
    	List<Deliveryaddress> deliveryAddressList = deliveryAddressDAO.selectAllDeliveryAddresss();
    	
    	Map<Integer, String> deliveryAddressMap = new HashMap<>();
    	
    	for(Deliveryaddress deliveryAddress: deliveryAddressList) {
    		String deliveryAddressString = deliveryAddress.getDeliveryAddressStreet1()+"\n "+deliveryAddress.getDeliveryAddressCity()+"\n "+deliveryAddress.getDeliveryAddressState();
    		deliveryAddressMap.put(deliveryAddress.getDeliveryAddressID(), deliveryAddressString);
    	}
    	
    	return deliveryAddressMap;
    }
    
    
    

}
