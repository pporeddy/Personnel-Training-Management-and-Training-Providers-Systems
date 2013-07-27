package com.prithvi.Pay.payCalc;

import java.sql.DriverManager;

public class Connect {

	public java.sql.Connection setConnection()
	{
		try {
	        Class.forName("com.mysql.jdbc.Driver"); 
	        System.out.println("Connected to db");
	    // driver
	    } catch (Exception x) {
	        System.out.println(x+" :Unable to load the driver class!");
	    }

	    java.sql.Connection dbConnection = null;
	    try {
	        String dbUrl = "jdbc:mysql://localhost:3306/pay";
	        String loginName = "root";
	        String password = "root";
	        dbConnection = DriverManager.getConnection(dbUrl, loginName,password);

	    } catch (Exception x) 
	    {
	        System.out.println("Couldn't get connection!" + x.getMessage());
	    }
		return dbConnection;
	
		
	}
	
}
