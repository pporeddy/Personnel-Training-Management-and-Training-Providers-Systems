package com.teju.trainingsystem.trainingReport;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.teju.www.Connect;

public class TrainingReportService {
	
	Connect con =new Connect();
	Connection dbConnection =null;
	
	public String getGrade(int person_id)
	{
		String res = null;
		
		try {
			   
		     dbConnection= (Connection) con.setConnection();
		    String query = "select grade from takes where person_id="+person_id;
			java.sql.PreparedStatement stmt = dbConnection.prepareStatement(query); 
		    ResultSet rs = stmt.executeQuery();
		    while(rs.next())
		    {
		    	
		    	if (Integer.parseInt(rs.getString(1))!=0)
		    	{
		    		res=res+","+rs.getString(1);
		    		System.out.println("res == "+res);
		    	}
		    }//while
		    } catch (SQLException e) {
				e.printStackTrace();
			}	
		return res;
	}
	
	
	public String getGradeYear(int person_id, String year)
	{
		String res =null;
		try {
			   
		     dbConnection= (Connection) con.setConnection();
		    String query = "select grade from takes where person_id="+person_id+"and year="+year;
			java.sql.PreparedStatement stmt = dbConnection.prepareStatement(query); 
		    ResultSet rs = stmt.executeQuery();
		    while(rs.next())
		    {
		    	res=res+","+rs.getString(1);
		    System.out.println(res);
		    }//while
		    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); 
			}	
		return res;
		
	}
	public String getGrade_session(int person_id, int session_id)
	{
		String res =null;
		try {
			   
		     dbConnection= (Connection) con.setConnection();
		    String query = "select grade from takes where person_id="+person_id+"and session_id="+session_id;
			java.sql.PreparedStatement stmt = dbConnection.prepareStatement(query); 
		    ResultSet rs = stmt.executeQuery();
		    while(rs.next())
		    {
		    	res=res+","+rs.getString(1);
		    System.out.println(res);
		    }//while
		    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		return res;
	}
}
