package com.abhi.connect.teju;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.abhi.personsystem.DbConnection;

public class ServTMSystemToTeju {
	
	java.sql.Connection con;
	DbConnection dbc=new DbConnection();
	
	List<Integer> result=new ArrayList<Integer>();
	
	public List<Integer> getSkillSet(int per_ID)
	{
		
		PreparedStatement stmt = null;
		con=(java.sql.Connection) dbc.getConnection();
		
		String query="select skill_id from per_skills where per_id ="+per_ID;
		
		try {
			stmt = con.prepareStatement(query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		 try 
		    {      
		        ResultSet rs = stmt.executeQuery();
		        
		       while(rs.next())
		        {
		        result.add(rs.getInt(1));
		        }//while   
		        con.close();
		        return result;
		    }
		    
		    catch (SQLException e)  {
		    		e.printStackTrace();
		    		}
		
		return result;
		}


	public String addPerSkills(int perid,int [] skillList)
	{
		con=(java.sql.Connection) dbc.getConnection();
		String query = null;
		PreparedStatement stmt; 
		int j=-1;
		try 
	    {
	    	for(int i=0;i<skillList.length;i++)
	    	{
	    	query = "insert into per_skills values ("+perid+","+skillList[i]+")";
	    	stmt = ((java.sql.Connection) con).prepareStatement(query); 
	        j= stmt.executeUpdate();
	    	}
	        con.close();
	    }   
	    catch (SQLException e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    }
		if (j>0)
		{
			query="Sucessfully updated";
			
		}
		else{ query="nothing to updated"; }
		return query;
	}
}
