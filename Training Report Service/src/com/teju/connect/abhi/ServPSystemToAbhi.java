package com.teju.connect.abhi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.teju.www.Connect;

public class ServPSystemToAbhi {
	
	Connect con =new Connect();
	Connection dbConnection =null;
	
	public List<Integer> getPositionSkillSet(int position_id)
	{
		System.out.println("courseInfo");	
		List<Integer> list = new ArrayList<Integer>();
		 try {
			   
			     dbConnection= (Connection) con.setConnection();
			    String query = "select skill_id from pos_skill where pos_code="+position_id	;
				java.sql.PreparedStatement stmt = dbConnection.prepareStatement(query); 
			    ResultSet rs = stmt.executeQuery();
			    while(rs.next())
			    {
			    	list.add(rs.getInt(1));
			    }//while
			    } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return list;
	}
}
