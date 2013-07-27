package com.teju.trainingsystem.catalogServices;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.teju.www.Connect;
public class CatalogService {
	Connect con =new Connect();
	Connection dbConnection =null;
	
	public List<String> courseInformation(int c_code)
	{
		System.out.println("courseInfo");	
		List<String> list = new ArrayList<String>();
		 try {
			   
			     dbConnection= (Connection) con.setConnection();
			    String query = "select knowledge_skill_code from knowledge_skills where c_code="+c_code	;
				java.sql.PreparedStatement stmt = dbConnection.prepareStatement(query); 
			    ResultSet rs = stmt.executeQuery();
			    while(rs.next())
			    {
			    	list.add(rs.getString(1));
			    System.out.println(rs.getString(1));
			    }//while
			    } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return list;
	}
	
	public List<String> courseInfoChange(String kowledge_title,Date date)
	{
	System.out.println("courseInfoChange");	
	List<String> list = new ArrayList<String>();
	 try {
		   
		     dbConnection= (Connection) con.setConnection();
		    String query = "select course_title from knowledge_skills natural join course where date between"+ date +"and curdate() and knowledge_title ="+kowledge_title;
		    
		    java.sql.PreparedStatement stmt = dbConnection.prepareStatement(query); 
		    ResultSet rs = stmt.executeQuery();
		    
		    while(rs.next())
		    {
		    	list.add(rs.getString(1));
		    System.out.println(rs.getString(1));
		    }//while
		    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return list;
	}
	
	public List<String> areaCall(String knowledge_title)
	{
		System.out.println("areaCall");	
		List<String> list = new ArrayList<String>();
		 try {
			   
			     dbConnection= (Connection) con.setConnection();
			    String query = "select c_code from knowledge_skills where knowledge_title='"+knowledge_title+"'";
				java.sql.PreparedStatement stmt = dbConnection.prepareStatement(query); 
			    ResultSet rs = stmt.executeQuery();
			    while(rs.next())
			    {
			    	list.add(rs.getString(1));
			    System.out.println(rs.getString(1));
			    }//while
			    } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return list;
	}

	public List<String> recommendeCourse(String knowledge_title)
	{
	
		System.out.println("recommendeCourse");	
		List<String> list = new ArrayList<String>();
		 try {
			   
			     dbConnection= (Connection) con.setConnection();
			    String query = "select course_title, c_code from knowledge_skills natural join course where knowledge_title="+knowledge_title;
			    		//"select knowledge_skill_code from knowledge_skills where c_code="+c_code	;
				java.sql.PreparedStatement stmt = dbConnection.prepareStatement(query); 
			    ResultSet rs = stmt.executeQuery();
			    while(rs.next())
			    {
			    	list.add(rs.getString(1));
			    System.out.println(rs.getString(1));
			    }//while
			    } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return list;
		
	}


}
