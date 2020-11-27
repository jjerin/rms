package com.unt.csce5350.rms.updated.model;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * The persistent class for the orderdetails database table.
 * 
 */
public class Orderdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private int menuItemId;

	private int menuItemQuantity;

	private BigDecimal orderDetailPrice;

	private String orderDetailsComments;

	public Orderdetail() {
	}
	
	public Orderdetail(int menuItemId, int menuItemQuantity, BigDecimal orderDetailPrice, String orderDetailsComments) {
		super();
		this.menuItemId = menuItemId;
		this.menuItemQuantity = menuItemQuantity;
		this.orderDetailPrice = orderDetailPrice;
		this.orderDetailsComments = orderDetailsComments;
	}

	public int getMenuItemQuantity() {
		return this.menuItemQuantity;
	}

	public void setMenuItemQuantity(int menuItemQuantity) {
		this.menuItemQuantity = menuItemQuantity;
	}

	public String getOrderDetailsComments() {
		return this.orderDetailsComments;
	}

	public void setOrderDetailsComments(String orderDetailsComments) {
		this.orderDetailsComments = orderDetailsComments;
	}

	public int getMenuItemId() {
		return menuItemId;
	}

	public void setMenuItemId(int menuItemId) {
		this.menuItemId = menuItemId;
	}

	public BigDecimal getOrderDetailPrice() {
		return orderDetailPrice;
	}

	public void setOrderDetailPrice(BigDecimal orderDetailPrice) {
		this.orderDetailPrice = orderDetailPrice;
	}

	@Override
	public String toString() {
		return "Orderdetail [menuItemId=" + menuItemId + ", menuItemQuantity=" + menuItemQuantity
				+ ", orderDetailPrice=" + orderDetailPrice + ", orderDetailsComments=" + orderDetailsComments + "]";
	}
	
	

}