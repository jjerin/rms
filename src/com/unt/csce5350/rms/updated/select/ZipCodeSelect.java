package com.unt.csce5350.rms.updated.select;

public class ZipCodeSelect {
	
    private String zipCodeId;
    private String zipCodeName;
    private String zipCodeDisplayName;

	public ZipCodeSelect() {
		super();
	}

	public ZipCodeSelect(String zipCodeId, String zipCodeName) {
		super();
		this.zipCodeId = zipCodeId;
		this.zipCodeName = zipCodeName;
		setupZipCodeDisplayName();
	}
	
	public String getZipCodeId() {
		return zipCodeId;
	}
	public void setZipCodeId(String zipCodeId) {
		this.zipCodeId = zipCodeId;
		setupZipCodeDisplayName();
	}
	public String getZipCodeName() {
		return zipCodeName;
	}
	public void setZipCodeName(String zipCodeName) {
		this.zipCodeName = zipCodeName;
		setupZipCodeDisplayName();
	}
	
	private void setupZipCodeDisplayName() {
		this.setZipCodeDisplayName(this.zipCodeId + " - " +this.zipCodeName);
	}

	public String getZipCodeDisplayName() {
		return zipCodeDisplayName;
	}

	public void setZipCodeDisplayName(String zipCodeDisplayName) {
		this.zipCodeDisplayName = zipCodeDisplayName;
	}

	@Override
	public String toString() {
		return "ZipCodeSelect [zipCodeId=" + zipCodeId + ", zipCodeName=" + zipCodeName + ", zipCodeDisplayName="
				+ zipCodeDisplayName + "]";
	}
	
}
	