package com.abhi.personsystem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonSkillPossession {
	java.sql.Connection con;
	DbConnection dbc=new DbConnection();
	
	public List<String> personSkill(int pid)
	{
		con=(java.sql.Connection) dbc.getConnection();
		List<String> result=new ArrayList<String>();
		
	    String query = "select skill_id from per_skills where per_id="+pid;
	    try 
	    {
	        PreparedStatement stmt = con.prepareStatement(query); 
	        ResultSet rs = stmt.executeQuery();
	        
	       while(rs.next())
	        {
	        result.add(rs.getString(1));
	        }//while
	        
	        con.close();
	        return result;
	        
	    }
	    
	    catch (SQLException e)  {
	    		e.printStackTrace();
	    		}
		
		return result;
	}
	
	
	public List<String> personCertificate(int pid)
	{
		con=(java.sql.Connection) dbc.getConnection();
		List<String> result=new ArrayList<String>();
		
	    String query = "select cert_id from per_cert where per_id="+pid;
	    try 
	    {
	        PreparedStatement stmt = ((java.sql.Connection) con).prepareStatement(query); 
	        ResultSet rs = stmt.executeQuery();
	        
	       while(rs.next())
	        {
	        result.add(rs.getString(1));
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
	
	public List<Integer> skillCall(int skillid)
	{
		con=(java.sql.Connection) dbc.getConnection();
		List<Integer> result=new ArrayList<Integer>();
		String query = "select count(per_id) from per_skills where per_id="+skillid;
	    try {
	        PreparedStatement stmt = ((java.sql.Connection) con).prepareStatement(query); 
	        ResultSet rs = stmt.executeQuery();
	        
	       while(rs.next()) { 
	        result.add(rs.getInt(1));
	        }//while
	        
	        con.close();
	        
	    }
	    
	    catch (SQLException e){
	    		e.printStackTrace();
	    		 }
	    return result;
		
	}
	
	
	public List<Integer> certificateCall(int certid)
	{
		con=(java.sql.Connection) dbc.getConnection();
		List<Integer> result=new ArrayList<Integer>();
		
	    String query = "select count(per_id) from per_cert where per_id="+certid;
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
	    		e.printStackTrace();		
	    }
		return result;
		
	}
}
