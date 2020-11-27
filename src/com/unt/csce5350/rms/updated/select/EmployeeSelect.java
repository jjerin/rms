package com.unt.csce5350.rms.updated.select;

public class EmployeeSelect {
	
    private int employeeId;
    private String employeeName;
    private String employeeDisplayName;

	public EmployeeSelect() {
		super();
	}

	public EmployeeSelect(int employeeId, String employeeName) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		setupEmployeeDisplayName();
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
		setupEmployeeDisplayName();
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
		setupEmployeeDisplayName();
	}
	
	private void setupEmployeeDisplayName() {
		this.setEmployeeDisplayName(this.employeeId + " - " +this.employeeName);
	}

	public String getEmployeeDisplayName() {
		return employeeDisplayName;
	}

	public void setEmployeeDisplayName(String employeeDisplayName) {
		this.employeeDisplayName = employeeDisplayName;
	}

	@Override
	public String toString() {
		return "EmployeeSelect [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeDisplayName="
				+ employeeDisplayName + "]";
	}
	
}
