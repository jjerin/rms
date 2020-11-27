package com.unt.csce5350.rms.updated.select;

public class MenuItemSelect {
	
    private int menuItemId;
    private String menuItemName;
    private String menuItemDisplayName;

	public MenuItemSelect() {
		super();
	}

	public MenuItemSelect(int menuItemId, String menuItemName) {
		super();
		this.menuItemId = menuItemId;
		this.menuItemName = menuItemName;
		setupMenuitemDisplayName();
	}
	
	public int getMenuItemId() {
		return menuItemId;
	}
	public void setMenuItemId(int menuItemId) {
		this.menuItemId = menuItemId;
		setupMenuitemDisplayName();
	}
	public String getMenuItemName() {
		return menuItemName;
	}
	public void setMenuItemName(String menuItemName) {
		this.menuItemName = menuItemName;
		setupMenuitemDisplayName();
	}
	
	private void setupMenuitemDisplayName() {
		this.setMenuItemDisplayName(this.menuItemId + " - " +this.menuItemName);
	}

	public String getMenuItemDisplayName() {
		return menuItemDisplayName;
	}

	public void setMenuItemDisplayName(String menuItemDisplayName) {
		this.menuItemDisplayName = menuItemDisplayName;
	}

	@Override
	public String toString() {
		return "MenuItemSelect [menuItemId=" + menuItemId + ", menuItemName=" + menuItemName + ", menuItemDisplayName="
				+ menuItemDisplayName + "]";
	}
	
}