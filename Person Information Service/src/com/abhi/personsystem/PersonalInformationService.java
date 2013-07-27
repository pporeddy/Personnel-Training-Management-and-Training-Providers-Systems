package com.abhi.personsystem;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PersonalInformationService {
	java.sql.Connection con;
	DbConnection dbc=new DbConnection();
	
	// Completed everything
	
	public List<String> getContactInfo(int pid)
	{
		con=(java.sql.Connection) dbc.getConnection();
		List<String> result=new ArrayList<String>();
	    
	    String query = "select * from person where per_id="+pid;
	    try 
	    {
	        PreparedStatement stmt = ((java.sql.Connection) con).prepareStatement(query); 
	        ResultSet rs = stmt.executeQuery();
	        
	       while(rs.next())
	        {
	        result.add(rs.getString(3)+","+rs.getString(4));
	        }//while
	        
	        con.close();
	        return result;
	        
	    }
	    
	    catch (SQLException e)
	    {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    		
	    }
		
		return result;
		
	}
	
	
	
	
	public List<String> personPositionInfo(int pid)
	{
		con=(java.sql.Connection) dbc.getConnection();
		List<String> result=new ArrayList<String>();
	    
	    String query = "select * from pos where pos_code in (select pos_code from per_pos natural join person where per_id="+pid+")";
	    try 
	    {
	        PreparedStatement stmt = ((java.sql.Connection) con).prepareStatement(query); 
	        ResultSet rs = stmt.executeQuery();
	        
	       while(rs.next())
	        {
	        result.add(rs.getString(2)+","+rs.getString(3));
	        }//while
	        
	        con.close();
	        return result;
	        
	    }
	    
	    catch (SQLException e)
	    {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    		
	    }
		
		return result;
		
	}
	
	
	public List<String> personSeniorityInfo(int pid)
	{
		con=(java.sql.Connection) dbc.getConnection();
		List<String> result=new ArrayList<String>();
	    
	    String query = "select year(now())-year(pos_appointment) as years,month(now())-month(pos_appointment) as month,day(now())-day(pos_appointment) as days from per_pos where per_id="+pid;
	    try 
	    {
	        PreparedStatement stmt = ((java.sql.Connection) con).prepareStatement(query); 
	        ResultSet rs = stmt.executeQuery();
	        
	       while(rs.next())
	        {
	        result.add(rs.getString(1)+" years "+rs.getString(2)+" months "+rs.getString(3)+" days");
	        }//while
	        
	        con.close();
	        return result;
	        
	    }
	    
	    catch (SQLException e)
	    {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    		
	    }
		
		return result;
		
	}
	
	
	
}
