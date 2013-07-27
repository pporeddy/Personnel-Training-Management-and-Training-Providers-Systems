package com.prithvi.connectFromTeju;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.teju.www.Connect;

public class SendtoPrithviFromTj {
	
	Connect con =new Connect();
	Connection dbConnection =null;

	public List<Integer> getSkillMenuForMaxPay(int company_id)
	{
		System.out.println("courseInfo");	
		List<Integer> list = new ArrayList<Integer>();
		 try {
			   
			    dbConnection= (Connection) con.setConnection();
			    String query = "select skill_id from pos_skill  natural join (select pos_code from job where status = 0 and company_id= '"+company_id+"') temp";
				java.sql.PreparedStatement stmt = dbConnection.prepareStatement(query); 
			    ResultSet rs = stmt.executeQuery();
			    while(rs.next())
			    {
			    	list.add(rs.getInt(1));
			    }//while
			    } catch (SQLException e) {
			   	  e.printStackTrace();
				}
		return list;
	}
	
	
}
