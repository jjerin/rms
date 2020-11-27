package com.unt.csce5350.rms.updated.select;

public class DeliveryPersonSelect {
	
    private int deliveryPersonId;
    private String deliveryPersonName;
    private String deliveryPersonDisplayName;

	public DeliveryPersonSelect() {
		super();
	}

	public DeliveryPersonSelect(int deliveryPersonId, String deliveryPersonName) {
		super();
		this.deliveryPersonId = deliveryPersonId;
		this.deliveryPersonName = deliveryPersonName;
		setupDeliveryPersonDisplayName();
	}
	
	public int getDeliveryPersonId() {
		return deliveryPersonId;
	}
	public void setDeliveryPersonId(int deliveryPersonId) {
		this.deliveryPersonId = deliveryPersonId;
		setupDeliveryPersonDisplayName();
	}
	public String getDeliveryPersonName() {
		return deliveryPersonName;
	}
	public void setDeliveryPersonName(String deliveryPersonName) {
		this.deliveryPersonName = deliveryPersonName;
		setupDeliveryPersonDisplayName();
	}
	
	private void setupDeliveryPersonDisplayName() {
		this.setDeliveryPersonDisplayName(this.deliveryPersonId + " - " +this.deliveryPersonName);
	}

	public String getDeliveryPersonDisplayName() {
		return deliveryPersonDisplayName;
	}

	public void setDeliveryPersonDisplayName(String deliveryPersonDisplayName) {
		this.deliveryPersonDisplayName = deliveryPersonDisplayName;
	}

	@Override
	public String toString() {
		return "DeliveryPersonSelect [deliveryPersonId=" + deliveryPersonId + ", deliveryPersonName=" + deliveryPersonName + ", deliveryPersonDisplayName="
				+ deliveryPersonDisplayName + "]";
	}
	
}