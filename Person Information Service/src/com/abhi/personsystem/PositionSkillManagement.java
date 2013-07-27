package com.abhi.personsystem;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PositionSkillManagement {
	java.sql.Connection con;
	DbConnection dbc=new DbConnection();
	
	public void addSkill(int posid,int skillid)
	{
		con=(java.sql.Connection) dbc.getConnection();
	    try 
	    {
	    	String query = "insert into pos_skill values ("+posid+","+skillid+")";
	        PreparedStatement stmt = ((java.sql.Connection) con).prepareStatement(query); 
	        stmt.executeQuery();
	        
	        
	        query = "insert into skills values ("+skillid+")";
	        stmt = ((java.sql.Connection) con).prepareStatement(query); 
	        stmt.executeQuery();
	        
	        
	        con.close();
	       
	        
	    }
	    
	    catch (SQLException e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    		
	    }		
	}
	
	
	public void dropSkill(int posid,int skillid)
	{
		con=(java.sql.Connection) dbc.getConnection();
		try 
	    {
	    	String query = "delete from pos_skill where pos_code='"+posid+"' and skill_id="+skillid;
	        PreparedStatement stmt = ((java.sql.Connection) con).prepareStatement(query); 
	        ResultSet rs= stmt.executeQuery();
	            if(rs.rowDeleted())
	            {
	            	System.out.println("Dropped skillid :"+skillid);
	            	
	            }
	            else
	            {System.out.println("Droping Faild");}
	        con.close();
	       
	    }
	    
	    catch (SQLException e)
	    {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    		
	    }
	}
	
	
	public List<String> positionSkill(int posid)
	{
		
		con=(java.sql.Connection) dbc.getConnection();
		List<String> result=new ArrayList<String>();

	    String query = "select s.skill_id as id,skill_name as skillname from skills s,pos_skill p where p.pos_code='"+posid+"' and p.skill_id=s.skill_id";
	    try 
	    {
	        PreparedStatement stmt = ((java.sql.Connection) con).prepareStatement(query); 
	        ResultSet rs = stmt.executeQuery();
	        
	       while(rs.next())
	        {
	        result.add(rs.getString(1));
	        }//while
	        
	        con.close();
	        
	    }
	    
	    catch (SQLException e)   {
	 	e.printStackTrace();
	    		
	    }
		
		return result;
			
	}
	
	
	public List<String> possiblePosition(int pid)
	{
		
		con=(java.sql.Connection) dbc.getConnection();
		List<String> result=new ArrayList<String>();
		
	    String query = "select s.skill_id as id,skill_name as skillname from skills s,pos_skill p where p.pos_code='"+pid+"' and p.skill_id=s.skill_id";
	    try 
	    {
	        PreparedStatement stmt = ((java.sql.Connection) con).prepareStatement(query); 
	        ResultSet rs = stmt.executeQuery();
	        
	       while(rs.next())
	        {
	    	   result.add(rs.getString(1));
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
