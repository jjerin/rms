package com.unt.csce5350.rms.updated.model;

import java.io.Serializable;


/**
 * The persistent class for the deliveryaddress database table.
 * 
 */
public class Deliveryaddress implements Serializable {
	private static final long serialVersionUID = 1L;

	private int deliveryAddressID;

	private String deliveryAddressCity;

	private String deliveryAddressState;

	private String deliveryAddressStreet1;

	private String deliveryAddressStreet2;

	private String deliveryAddressZip;

	public Deliveryaddress() {
	}
	
	public Deliveryaddress(int deliveryAddressID, String deliveryAddressCity, String deliveryAddressState,
			String deliveryAddressStreet1, String deliveryAddressStreet2, String deliveryAddressZip) {
		super();
		this.deliveryAddressID = deliveryAddressID;
		this.deliveryAddressCity = deliveryAddressCity;
		this.deliveryAddressState = deliveryAddressState;
		this.deliveryAddressStreet1 = deliveryAddressStreet1;
		this.deliveryAddressStreet2 = deliveryAddressStreet2;
		this.deliveryAddressZip = deliveryAddressZip;
	}
	
	public Deliveryaddress(String deliveryAddressCity, String deliveryAddressState, String deliveryAddressStreet1,
			String deliveryAddressStreet2, String deliveryAddressZip) {
		super();
		this.deliveryAddressCity = deliveryAddressCity;
		this.deliveryAddressState = deliveryAddressState;
		this.deliveryAddressStreet1 = deliveryAddressStreet1;
		this.deliveryAddressStreet2 = deliveryAddressStreet2;
		this.deliveryAddressZip = deliveryAddressZip;
	}

	public int getDeliveryAddressID() {
		return this.deliveryAddressID;
	}

	public void setDeliveryAddressID(int deliveryAddressID) {
		this.deliveryAddressID = deliveryAddressID;
	}

	public String getDeliveryAddressCity() {
		return this.deliveryAddressCity;
	}

	public void setDeliveryAddressCity(String deliveryAddressCity) {
		this.deliveryAddressCity = deliveryAddressCity;
	}

	public String getDeliveryAddressState() {
		return this.deliveryAddressState;
	}

	public void setDeliveryAddressState(String deliveryAddressState) {
		this.deliveryAddressState = deliveryAddressState;
	}

	public String getDeliveryAddressStreet1() {
		return this.deliveryAddressStreet1;
	}

	public void setDeliveryAddressStreet1(String deliveryAddressStreet1) {
		this.deliveryAddressStreet1 = deliveryAddressStreet1;
	}

	public String getDeliveryAddressStreet2() {
		return this.deliveryAddressStreet2;
	}

	public void setDeliveryAddressStreet2(String deliveryAddressStreet2) {
		this.deliveryAddressStreet2 = deliveryAddressStreet2;
	}

	public String getDeliveryAddressZip() {
		return this.deliveryAddressZip;
	}

	public void setDeliveryAddressZip(String deliveryAddressZip) {
		this.deliveryAddressZip = deliveryAddressZip;
	}

}