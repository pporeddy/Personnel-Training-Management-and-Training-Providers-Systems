package com.abhi.personsystem;
import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	String result="";
	public Connection getConnection()
	{
	try {
        Class.forName("com.mysql.jdbc.Driver"); 
    // driver
    } catch (Exception x) {
        System.out.println(x+" :Unable to load the driver class!");
    }

    java.sql.Connection dbConnection = null;
    try {
        String dbUrl = "jdbc:mysql://localhost:3306/personsystem";
        String loginName = "root";
        String password = "root";
        dbConnection = DriverManager.getConnection(dbUrl, loginName,password);
        result="Connection was successful";

    } catch (Exception x) 
    {
        System.out.println("Couldn't get connection!" + x.getMessage());
        result="Connection failure";
    }
	return dbConnection;
	
	}
}
