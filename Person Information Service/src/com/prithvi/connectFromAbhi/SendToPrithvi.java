package com.prithvi.connectFromAbhi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.abhi.personsystem.DbConnection;

public class SendToPrithvi {
	
	java.sql.Connection con;
	DbConnection dbc=new DbConnection();
	

	public List<Integer> possiblePosition(int pid)
	{
		
		con=(java.sql.Connection) dbc.getConnection();
		List<Integer> result=new ArrayList<Integer>();
		
	    String query = "select skill_id from per_skills where per_id="+pid;
	    try 
	    {
	        PreparedStatement stmt = ((java.sql.Connection) con).prepareStatement(query); 
	        ResultSet rs = stmt.executeQuery();
	        
	       while(rs.next())
	        {
	    	   result.add(rs.getInt(1));
	        }//while
	        
	        con.close();
	        
	    }
	    
	    catch (SQLException e)
	    {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    		
	    }
		
		return result;
		
	}
	
}
