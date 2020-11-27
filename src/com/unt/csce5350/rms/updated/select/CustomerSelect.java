package com.unt.csce5350.rms.updated.select;

public class CustomerSelect {
	
    private int customerId;
    private String customerName;
    private String customerDisplayName;

	public CustomerSelect() {
		super();
	}

	public CustomerSelect(int customerId, String customerName) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		setupCustomerDisplayName();
	}
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
		setupCustomerDisplayName();
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
		setupCustomerDisplayName();
	}
	
	private void setupCustomerDisplayName() {
		this.setCustomerDisplayName(this.customerId + " - " +this.customerName);
	}

	public String getCustomerDisplayName() {
		return customerDisplayName;
	}

	public void setCustomerDisplayName(String customerDisplayName) {
		this.customerDisplayName = customerDisplayName;
	}

	@Override
	public String toString() {
		return "CustomerSelect [customerId=" + customerId + ", customerName=" + customerName + ", customerDisplayName="
				+ customerDisplayName + "]";
	}
	
}
	