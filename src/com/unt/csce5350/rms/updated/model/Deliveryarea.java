package com.unt.csce5350.rms.updated.model;

import java.io.Serializable;


/**
 * The persistent class for the deliveryarea database table.
 * 
 */
public class Deliveryarea implements Serializable {
	private static final long serialVersionUID = 1L;

	private int deliveryAreaID;

	private String deliveryAreaName;
	
	private int deliveryAreaZip;


	public Deliveryarea() {
	}
	
	public Deliveryarea(int deliveryAreaID, String deliveryAreaName, int deliveryAreaZip) {
		super();
		this.deliveryAreaID = deliveryAreaID;
		this.deliveryAreaName = deliveryAreaName;
		this.deliveryAreaZip = deliveryAreaZip;
	}

	public Deliveryarea(String deliveryAreaName, int deliveryAreaZip) {
		super();
		this.deliveryAreaName = deliveryAreaName;
		this.deliveryAreaZip = deliveryAreaZip;
	}

	public int getDeliveryAreaID() {
		return this.deliveryAreaID;
	}

	public void setDeliveryAreaID(int deliveryAreaID) {
		this.deliveryAreaID = deliveryAreaID;
	}

	public String getDeliveryAreaName() {
		return this.deliveryAreaName;
	}

	public void setDeliveryAreaName(String deliveryAreaName) {
		this.deliveryAreaName = deliveryAreaName;
	}

	public int getDeliveryAreaZip() {
		return deliveryAreaZip;
	}

	public void setDeliveryAreaZip(int deliveryAreaZip) {
		this.deliveryAreaZip = deliveryAreaZip;
	}
}