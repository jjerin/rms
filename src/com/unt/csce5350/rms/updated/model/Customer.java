package com.unt.csce5350.rms.updated.model;

import java.io.Serializable;


/**
 * The persistent class for the customer database table.
 * 
 */
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	private int customerID;

	private String customerCity;

	private int customerCurrentDiscount;

	private String customerEmail;

	private String customerFirstName;

	private String customerLastName;

	private int customerOrders;

	private String customerPhone;

	private String customerState;

	private String customerStreet1;

	private String customerStreet2;

	private String customerZip;

	public Customer() {
	}

	
	
	public Customer(int customerID, String customerCity, int customerCurrentDiscount, String customerEmail,
			String customerFirstName, String customerLastName, int customerOrders, String customerPhone,
			String customerState, String customerStreet1, String customerStreet2, String customerZip) {
		super();
		this.customerID = customerID;
		this.customerCity = customerCity;
		this.customerCurrentDiscount = customerCurrentDiscount;
		this.customerEmail = customerEmail;
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.customerOrders = customerOrders;
		this.customerPhone = customerPhone;
		this.customerState = customerState;
		this.customerStreet1 = customerStreet1;
		this.customerStreet2 = customerStreet2;
		this.customerZip = customerZip;
	}

	public Customer(String customerCity, int customerCurrentDiscount, String customerEmail,
			String customerFirstName, String customerLastName, int customerOrders, String customerPhone,
			String customerState, String customerStreet1, String customerStreet2, String customerZip) {
		super();
		this.customerCity = customerCity;
		this.customerCurrentDiscount = customerCurrentDiscount;
		this.customerEmail = customerEmail;
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.customerOrders = customerOrders;
		this.customerPhone = customerPhone;
		this.customerState = customerState;
		this.customerStreet1 = customerStreet1;
		this.customerStreet2 = customerStreet2;
		this.customerZip = customerZip;
	}


	public int getCustomerID() {
		return this.customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getCustomerCity() {
		return this.customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	public int getCustomerCurrentDiscount() {
		return this.customerCurrentDiscount;
	}

	public void setCustomerCurrentDiscount(int customerCurrentDiscount) {
		this.customerCurrentDiscount = customerCurrentDiscount;
	}

	public String getCustomerEmail() {
		return this.customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerFirstName() {
		return this.customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return this.customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public int getCustomerOrders() {
		return this.customerOrders;
	}

	public void setCustomerOrders(int customerOrders) {
		this.customerOrders = customerOrders;
	}

	public String getCustomerPhone() {
		return this.customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerState() {
		return this.customerState;
	}

	public void setCustomerState(String customerState) {
		this.customerState = customerState;
	}

	public String getCustomerStreet1() {
		return this.customerStreet1;
	}

	public void setCustomerStreet1(String customerStreet1) {
		this.customerStreet1 = customerStreet1;
	}

	public String getCustomerStreet2() {
		return this.customerStreet2;
	}

	public void setCustomerStreet2(String customerStreet2) {
		this.customerStreet2 = customerStreet2;
	}

	public String getCustomerZip() {
		return this.customerZip;
	}

	public void setCustomerZip(String customerZip) {
		this.customerZip = customerZip;
	}



	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", customerCity=" + customerCity + ", customerCurrentDiscount="
				+ customerCurrentDiscount + ", customerEmail=" + customerEmail + ", customerFirstName="
				+ customerFirstName + ", customerLastName=" + customerLastName + ", customerOrders=" + customerOrders
				+ ", customerPhone=" + customerPhone + ", customerState=" + customerState + ", customerStreet1="
				+ customerStreet1 + ", customerStreet2=" + customerStreet2 + ", customerZip=" + customerZip + "]";
	}


}