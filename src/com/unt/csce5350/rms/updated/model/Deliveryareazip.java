package com.unt.csce5350.rms.updated.model;

import java.io.Serializable;


/**
 * The persistent class for the deliveryareazip database table.
 * 
 */
public class Deliveryareazip implements Serializable {
	private static final long serialVersionUID = 1L;

	private int deliveryAreaZipCode;

	private int deliveryAreaID;

	public Deliveryareazip() {
	}

	public int getDeliveryAreaZipCode() {
		return this.deliveryAreaZipCode;
	}

	public void setDeliveryAreaZipCode(int deliveryAreaZipCode) {
		this.deliveryAreaZipCode = deliveryAreaZipCode;
	}

	public int getDeliveryAreaID() {
		return this.deliveryAreaID;
	}

	public void setDeliveryAreaID(int deliveryAreaID) {
		this.deliveryAreaID = deliveryAreaID;
	}

}