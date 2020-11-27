package com.unt.csce5350.rms.updated.model;

import java.io.Serializable;


/**
 * The persistent class for the employee database table.
 * 
 */
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	private int employeeID;

	private String employeeCity;

	private String employeeFirstName;

	private String employeeLastName;

	private String employeePhone;

	private String employeeState;

	private String employeeStreet1;

	private String employeeStreet2;

	private String employeeZip;

	public Employee() {
	}
	
	

	public Employee(int employeeID, String employeeCity, String employeeFirstName, String employeeLastName,
			String employeePhone, String employeeState, String employeeStreet1, String employeeStreet2,
			String employeeZip) {
		super();
		this.employeeID = employeeID;
		this.employeeCity = employeeCity;
		this.employeeFirstName = employeeFirstName;
		this.employeeLastName = employeeLastName;
		this.employeePhone = employeePhone;
		this.employeeState = employeeState;
		this.employeeStreet1 = employeeStreet1;
		this.employeeStreet2 = employeeStreet2;
		this.employeeZip = employeeZip;
	}
	
	
	public Employee(String employeeCity, String employeeFirstName, String employeeLastName,
			String employeePhone, String employeeState, String employeeStreet1, String employeeStreet2,
			String employeeZip) {
		super();
		this.employeeCity = employeeCity;
		this.employeeFirstName = employeeFirstName;
		this.employeeLastName = employeeLastName;
		this.employeePhone = employeePhone;
		this.employeeState = employeeState;
		this.employeeStreet1 = employeeStreet1;
		this.employeeStreet2 = employeeStreet2;
		this.employeeZip = employeeZip;
	}




	public int getEmployeeID() {
		return this.employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getEmployeeCity() {
		return this.employeeCity;
	}

	public void setEmployeeCity(String employeeCity) {
		this.employeeCity = employeeCity;
	}

	public String getEmployeeFirstName() {
		return this.employeeFirstName;
	}

	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}

	public String getEmployeeLastName() {
		return this.employeeLastName;
	}

	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}

	public String getEmployeePhone() {
		return this.employeePhone;
	}

	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}

	public String getEmployeeState() {
		return this.employeeState;
	}

	public void setEmployeeState(String employeeState) {
		this.employeeState = employeeState;
	}

	public String getEmployeeStreet1() {
		return this.employeeStreet1;
	}

	public void setEmployeeStreet1(String employeeStreet1) {
		this.employeeStreet1 = employeeStreet1;
	}

	public String getEmployeeStreet2() {
		return this.employeeStreet2;
	}

	public void setEmployeeStreet2(String employeeStreet2) {
		this.employeeStreet2 = employeeStreet2;
	}

	public String getEmployeeZip() {
		return this.employeeZip;
	}

	public void setEmployeeZip(String employeeZip) {
		this.employeeZip = employeeZip;
	}

}