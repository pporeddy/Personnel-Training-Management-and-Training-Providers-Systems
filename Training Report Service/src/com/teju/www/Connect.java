package com.teju.www;

import java.sql.DriverManager;

public class Connect {
	public void init()
	{
		System.out.println("In init");
		
	}
	public java.sql.Connection setConnection()
	{
		try {
	        Class.forName("com.mysql.jdbc.Driver"); 
	    // driver
	    } catch (Exception x) {
	        System.out.println(x+" :Unable to load the driver class!");
	    }

	    java.sql.Connection dbConnection = null;
	    try {
	        String dbUrl = "jdbc:mysql://localhost:3306/TrainingSystem";
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
