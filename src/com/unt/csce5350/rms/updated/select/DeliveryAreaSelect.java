package com.unt.csce5350.rms.updated.select;

public class DeliveryAreaSelect {
	
    private int deliveryAreaId;
    private String deliveryAreaName;
    private String deliveryAreaDisplayName;

	public DeliveryAreaSelect() {
		super();
	}

	public DeliveryAreaSelect(int deliveryAreaId, String deliveryAreaName) {
		super();
		this.deliveryAreaId = deliveryAreaId;
		this.deliveryAreaName = deliveryAreaName;
		setupDeliveryAreaDisplayName();
	}
	
	public int getDeliveryAreaId() {
		return deliveryAreaId;
	}
	public void setDeliveryAreaId(int deliveryAreaId) {
		this.deliveryAreaId = deliveryAreaId;
		setupDeliveryAreaDisplayName();
	}
	public String getDeliveryAreaName() {
		return deliveryAreaName;
	}
	public void setDeliveryAreaName(String deliveryAreaName) {
		this.deliveryAreaName = deliveryAreaName;
		setupDeliveryAreaDisplayName();
	}
	
	private void setupDeliveryAreaDisplayName() {
		this.setDeliveryAreaDisplayName(this.deliveryAreaId + " - " +this.deliveryAreaName);
	}

	public String getDeliveryAreaDisplayName() {
		return deliveryAreaDisplayName;
	}

	public void setDeliveryAreaDisplayName(String deliveryAreaDisplayName) {
		this.deliveryAreaDisplayName = deliveryAreaDisplayName;
	}

	@Override
	public String toString() {
		return "DeliveryAreaSelect [deliveryAreaId=" + deliveryAreaId + ", deliveryAreaName=" + deliveryAreaName + ", deliveryAreaDisplayName="
				+ deliveryAreaDisplayName + "]";
	}
	
}
	