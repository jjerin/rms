package com.unt.csce5350.rms.utils;

public interface RMSConstants {
	
	
    // public String jdbcURL = "jdbc:mysql://localhost:3306/jerin?useSSL=false&allowPublicKeyRetrieval=true";
	// public String jdbcURL = "jdbc:mysql://localhost:3306/rj_burgers?useSSL=false&allowPublicKeyRetrieval=true";

	
	//public String dbSchema = "jerin";
	//public String dbSchema = "rj_burgers";
	public String dbSchema = "r_j_burgers";
	public String jdbcDriverClass = "com.mysql.cj.jdbc.Driver";
	public String jdbcURL = "jdbc:mysql://localhost:3306/"+dbSchema+"?useSSL=false&allowPublicKeyRetrieval=true";
    public String jdbcUserName = "root";
    public String jdbcPassword = "jerin";

}
