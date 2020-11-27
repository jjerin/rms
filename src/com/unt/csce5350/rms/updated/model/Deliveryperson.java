package com.unt.csce5350.rms.updated.model;

import java.io.Serializable;


/**
 * The persistent class for the deliveryperson database table.
 * 
 */
public class Deliveryperson implements Serializable {
	private static final long serialVersionUID = 1L;

	private int deliveryPersonID;

	private String deliveryPersonCity;

	private String deliveryPersonFirstName;

	private String deliveryPersonLastName;

	private String deliveryPersonPhone;

	private String deliveryPersonState;

	private String deliveryPersonStreet1;

	private String deliveryPersonStreet2;

	private String deliveryPersonZip;
	
	private int deliveryAreaID;

	public Deliveryperson() {
	}
	
	

	public Deliveryperson(int deliveryPersonID, String deliveryPersonCity, String deliveryPersonFirstName,
			String deliveryPersonLastName, String deliveryPersonPhone, String deliveryPersonState,
			String deliveryPersonStreet1, String deliveryPersonStreet2, String deliveryPersonZip, int deliveryAreaID) {
		super();
		this.deliveryPersonID = deliveryPersonID;
		this.deliveryPersonCity = deliveryPersonCity;
		this.deliveryPersonFirstName = deliveryPersonFirstName;
		this.deliveryPersonLastName = deliveryPersonLastName;
		this.deliveryPersonPhone = deliveryPersonPhone;
		this.deliveryPersonState = deliveryPersonState;
		this.deliveryPersonStreet1 = deliveryPersonStreet1;
		this.deliveryPersonStreet2 = deliveryPersonStreet2;
		this.deliveryPersonZip = deliveryPersonZip;
		this.deliveryAreaID = deliveryAreaID;
	}
	
	public Deliveryperson(String deliveryPersonCity, String deliveryPersonFirstName, String deliveryPersonLastName,
			String deliveryPersonPhone, String deliveryPersonState, String deliveryPersonStreet1,
			String deliveryPersonStreet2, String deliveryPersonZip, int deliveryAreaID) {
		super();
		this.deliveryPersonCity = deliveryPersonCity;
		this.deliveryPersonFirstName = deliveryPersonFirstName;
		this.deliveryPersonLastName = deliveryPersonLastName;
		this.deliveryPersonPhone = deliveryPersonPhone;
		this.deliveryPersonState = deliveryPersonState;
		this.deliveryPersonStreet1 = deliveryPersonStreet1;
		this.deliveryPersonStreet2 = deliveryPersonStreet2;
		this.deliveryPersonZip = deliveryPersonZip;
		this.deliveryAreaID = deliveryAreaID;
	}



	public int getDeliveryPersonID() {
		return this.deliveryPersonID;
	}

	public void setDeliveryPersonID(int deliveryPersonID) {
		this.deliveryPersonID = deliveryPersonID;
	}

	public String getDeliveryPersonCity() {
		return this.deliveryPersonCity;
	}

	public void setDeliveryPersonCity(String deliveryPersonCity) {
		this.deliveryPersonCity = deliveryPersonCity;
	}

	public String getDeliveryPersonFirstName() {
		return this.deliveryPersonFirstName;
	}

	public void setDeliveryPersonFirstName(String deliveryPersonFirstName) {
		this.deliveryPersonFirstName = deliveryPersonFirstName;
	}

	public String getDeliveryPersonLastName() {
		return this.deliveryPersonLastName;
	}

	public void setDeliveryPersonLastName(String deliveryPersonLastName) {
		this.deliveryPersonLastName = deliveryPersonLastName;
	}

	public String getDeliveryPersonPhone() {
		return this.deliveryPersonPhone;
	}

	public void setDeliveryPersonPhone(String deliveryPersonPhone) {
		this.deliveryPersonPhone = deliveryPersonPhone;
	}

	public String getDeliveryPersonState() {
		return this.deliveryPersonState;
	}

	public void setDeliveryPersonState(String deliveryPersonState) {
		this.deliveryPersonState = deliveryPersonState;
	}

	public String getDeliveryPersonStreet1() {
		return this.deliveryPersonStreet1;
	}

	public void setDeliveryPersonStreet1(String deliveryPersonStreet1) {
		this.deliveryPersonStreet1 = deliveryPersonStreet1;
	}

	public String getDeliveryPersonStreet2() {
		return this.deliveryPersonStreet2;
	}

	public void setDeliveryPersonStreet2(String deliveryPersonStreet2) {
		this.deliveryPersonStreet2 = deliveryPersonStreet2;
	}

	public String getDeliveryPersonZip() {
		return this.deliveryPersonZip;
	}

	public void setDeliveryPersonZip(String deliveryPersonZip) {
		this.deliveryPersonZip = deliveryPersonZip;
	}

	public int getDeliveryAreaID() {
		return deliveryAreaID;
	}

	public void setDeliveryAreaID(int deliveryAreaID) {
		this.deliveryAreaID = deliveryAreaID;
	}

}