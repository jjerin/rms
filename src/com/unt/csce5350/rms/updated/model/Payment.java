package com.unt.csce5350.rms.updated.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;


/**
 * The persistent class for the payment database table.
 * 
 */
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	private int paymentID;

	private int orderID;

	private Date paymentDate;

	private BigDecimal paymentTotalPaid;

	private String paymentType;

	public Payment() {
	}
	
	
	

	public Payment(int paymentID, int orderID, Date paymentDate, BigDecimal paymentTotalPaid, String paymentType) {
		super();
		this.paymentID = paymentID;
		this.orderID = orderID;
		this.paymentDate = paymentDate;
		this.paymentTotalPaid = paymentTotalPaid;
		this.paymentType = paymentType;
	}


	


	public Payment(int orderID, Date paymentDate, BigDecimal paymentTotalPaid, String paymentType) {
		super();
		this.orderID = orderID;
		this.paymentDate = paymentDate;
		this.paymentTotalPaid = paymentTotalPaid;
		this.paymentType = paymentType;
	}




	public int getPaymentID() {
		return this.paymentID;
	}

	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	public int getOrderID() {
		return this.orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Date getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public BigDecimal getPaymentTotalPaid() {
		return this.paymentTotalPaid;
	}

	public void setPaymentTotalPaid(BigDecimal paymentTotalPaid) {
		this.paymentTotalPaid = paymentTotalPaid;
	}

	public String getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

}