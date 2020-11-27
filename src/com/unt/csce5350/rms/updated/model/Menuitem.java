package com.unt.csce5350.rms.updated.model;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * The persistent class for the menuitem database table.
 * 
 */
public class Menuitem implements Serializable {
	private static final long serialVersionUID = 1L;

	private int menuItemID;

	private String menuItemDescription;

	private String menuItemName;

	private BigDecimal menuItemPrice;

	private String menuItemType;

	public Menuitem() {
	}
	
	

	public Menuitem(int menuItemID, String menuItemDescription, String menuItemName, BigDecimal menuItemPrice,
			String menuItemType) {
		super();
		this.menuItemID = menuItemID;
		this.menuItemDescription = menuItemDescription;
		this.menuItemName = menuItemName;
		this.menuItemPrice = menuItemPrice;
		this.menuItemType = menuItemType;
	}

	public Menuitem(String menuItemDescription, String menuItemName, BigDecimal menuItemPrice,
			String menuItemType) {
		super();
		this.menuItemDescription = menuItemDescription;
		this.menuItemName = menuItemName;
		this.menuItemPrice = menuItemPrice;
		this.menuItemType = menuItemType;
	}


	public int getMenuItemID() {
		return this.menuItemID;
	}

	public void setMenuItemID(int menuItemID) {
		this.menuItemID = menuItemID;
	}

	public String getMenuItemDescription() {
		return this.menuItemDescription;
	}

	public void setMenuItemDescription(String menuItemDescription) {
		this.menuItemDescription = menuItemDescription;
	}

	public String getMenuItemName() {
		return this.menuItemName;
	}

	public void setMenuItemName(String menuItemName) {
		this.menuItemName = menuItemName;
	}

	public BigDecimal getMenuItemPrice() {
		return this.menuItemPrice;
	}

	public void setMenuItemPrice(BigDecimal menuItemPrice) {
		this.menuItemPrice = menuItemPrice;
	}

	public String getMenuItemType() {
		return this.menuItemType;
	}

	public void setMenuItemType(String menuItemType) {
		this.menuItemType = menuItemType;
	}

}