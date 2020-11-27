package com.unt.csce5350.rms.updated.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the order database table.
 * 
 */
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	private int orderID;

	private Date orderDateTime;

	private BigDecimal orderTotalCost;

	private String orderType;
	
	private int employeeID;
	private int deliveryPersonID;
	private int customerID;
	private int deliveryAddressID;
	
	
	
	private String employeeDisplay;
	private String customerDisplay;
	private String deliveryPersonDisplay;
	private String deliveryAddressDisplay;
	
	private List<Orderdetail> orderDetailList = new ArrayList<>();;



	public Order() {
		/*for(int i=0;i<5;i++) {
			Orderdetail od = new Orderdetail();
			od.setMenuItemId(1);
			od.setMenuItemQuantity(3);
			od.setOrderDetailPrice(new BigDecimal(1.32));
			od.setOrderDetailsComments("to-go");
			orderDetailList.add(od);
		}*/
	}
	
	

	public Order(int orderID, Date orderDateTime, BigDecimal orderTotalCost, String orderType, int employeeID,
			int deliveryPersonID, int customerID, int deliveryAddressID) {
		this();
		this.orderID = orderID;
		this.orderDateTime = orderDateTime;
		this.orderTotalCost = orderTotalCost;
		this.orderType = orderType;
		this.employeeID = employeeID;
		this.deliveryPersonID = deliveryPersonID;
		this.customerID = customerID;
		this.deliveryAddressID = deliveryAddressID;
	}
	
	
	



	public Order(Date orderDateTime, BigDecimal orderTotalCost, String orderType, int employeeID, int deliveryPersonID,
			int customerID, int deliveryAddressID) {
		this();
		this.orderDateTime = orderDateTime;
		this.orderTotalCost = orderTotalCost;
		this.orderType = orderType;
		this.employeeID = employeeID;
		this.deliveryPersonID = deliveryPersonID;
		this.customerID = customerID;
		this.deliveryAddressID = deliveryAddressID;
	}



	public int getOrderID() {
		return this.orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Date getOrderDateTime() {
		return this.orderDateTime;
	}

	public void setOrderDateTime(Date orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	public BigDecimal getOrderTotalCost() {
		return this.orderTotalCost;
	}

	public void setOrderTotalCost(BigDecimal orderTotalCost) {
		this.orderTotalCost = orderTotalCost;
	}

	public String getOrderType() {
		return this.orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public int getDeliveryPersonID() {
		return deliveryPersonID;
	}

	public void setDeliveryPersonID(int deliveryPersonID) {
		this.deliveryPersonID = deliveryPersonID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getDeliveryAddressID() {
		return deliveryAddressID;
	}

	public void setDeliveryAddressID(int deliveryAddressID) {
		this.deliveryAddressID = deliveryAddressID;
	}
	public List<Orderdetail> getOrderDetailList() {
		return orderDetailList;
	}



	public void setOrderDetailList(List<Orderdetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}



	public String getEmployeeDisplay() {
		return employeeDisplay;
	}



	public void setEmployeeDisplay(String employeeDisplay) {
		this.employeeDisplay = employeeDisplay;
	}



	public String getCustomerDisplay() {
		return customerDisplay;
	}



	public void setCustomerDisplay(String customerDisplay) {
		this.customerDisplay = customerDisplay;
	}



	public String getDeliveryPersonDisplay() {
		return deliveryPersonDisplay;
	}



	public void setDeliveryPersonDisplay(String deliveryPersonDisplay) {
		this.deliveryPersonDisplay = deliveryPersonDisplay;
	}



	public String getDeliveryAddressDisplay() {
		return deliveryAddressDisplay;
	}



	public void setDeliveryAddressDisplay(String deliveryAddressDisplay) {
		this.deliveryAddressDisplay = deliveryAddressDisplay;
	}



	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", orderDateTime=" + orderDateTime + ", orderTotalCost=" + orderTotalCost
				+ ", orderType=" + orderType + ", employeeID=" + employeeID + ", deliveryPersonID=" + deliveryPersonID
				+ ", customerID=" + customerID + ", deliveryAddressID=" + deliveryAddressID + ", orderDetailList="
				+ orderDetailList + "]";
	}
	
	
	

}