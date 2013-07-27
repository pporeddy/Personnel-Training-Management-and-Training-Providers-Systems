package com.prithvi.Pay.payCalc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;


public class PayCalcService{
	Connect con =new Connect();
	Connection dbConnection= null;
	
	
	public double personPayable(int personID)
	{
		double dd =0.0;
		try{ dbConnection= (Connection) con.setConnection();
		} catch (Exception e){
		System.out.println("Error is:" +e);}
		
		String query = "select ID,pay from payslip where ID="+personID;
		ResultSet rs = null;
		try {
    PreparedStatement stmt = dbConnection.prepareStatement(query); 
    rs = stmt.executeQuery();
   
    while(rs.next())
    {
    System.out.println(rs.getString("ID")+","+rs.getString("PAY"));
    dd=Double.parseDouble(rs.getString("PAY"));
    }//while
    dbConnection.close();
    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
 
    
	return dd;
	}// end of PersonPayable
	
	
	
	
	
	public int makePayroll(int departmentID)
	{
		int send =-1;
	
		dbConnection= (Connection) con.setConnection();
		String query = "select pay from payslip where E_ID="+departmentID;
    
	try {
    PreparedStatement stmt = dbConnection.prepareStatement(query); 
    ResultSet rs = stmt.executeQuery();
    
    while(rs.next())
    {
    	send=Integer.parseInt(rs.getString(1));
    	System.out.println(send);
    }//while
    
    dbConnection.close();
    
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return send;
	}// end of makePayroll
	
}//Class
