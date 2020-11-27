package com.unt.csce5350.rms.updated.select;

public class OrderTypeSelect {
	
    private String orderTypeId;
    private String orderTypeName;
    private String orderTypeDisplayName;

	public OrderTypeSelect() {
		super();
	}

	public OrderTypeSelect(String orderTypeId, String orderTypeName) {
		super();
		this.orderTypeId = orderTypeId;
		this.orderTypeName = orderTypeName;
		setupOrderTypeDisplayName();
	}
	
	public String getOrderTypeId() {
		return orderTypeId;
	}
	public void setOrderTypeId(String orderTypeId) {
		this.orderTypeId = orderTypeId;
		setupOrderTypeDisplayName();
	}
	public String getOrderTypeName() {
		return orderTypeName;
	}
	public void setOrderTypeName(String orderTypeName) {
		this.orderTypeName = orderTypeName;
		setupOrderTypeDisplayName();
	}
	
	private void setupOrderTypeDisplayName() {
		this.setOrderTypeDisplayName(this.orderTypeId + " - " +this.orderTypeName);
	}

	public String getOrderTypeDisplayName() {
		return orderTypeDisplayName;
	}

	public void setOrderTypeDisplayName(String orderTypeDisplayName) {
		this.orderTypeDisplayName = orderTypeDisplayName;
	}

	@Override
	public String toString() {
		return "OrderTypeSelect [orderTypeId=" + orderTypeId + ", orderTypeName=" + orderTypeName + ", orderTypeDisplayName="
				+ orderTypeDisplayName + "]";
	}
	
}
	