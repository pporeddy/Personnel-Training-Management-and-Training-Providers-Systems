package com.teju.trainingsystem.trainingCounceling;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.AxisFault;

import com.abhi.connect.teju.ServTMSystemToTejuStub;
import com.abhi.connect.teju.ServTMSystemToTejuStub.AddPerSkills;
import com.abhi.connect.teju.ServTMSystemToTejuStub.AddPerSkillsResponse;
import com.abhi.connect.teju.ServTMSystemToTejuStub.GetSkillSet;
import com.abhi.connect.teju.ServTMSystemToTejuStub.GetSkillSetResponse;
import com.teju.www.Connect;

public class TrainingCouncellingService {
	
	Connect con =new Connect();
	Connection dbConnection =null;
	
	
	public List<Integer> missingSkill(int person_id, int position_id)
	{
		//division must be performed on the below tables
		List<Integer> result = new ArrayList<Integer>();
		List<Integer> posSkill = new ArrayList<Integer>();
		List<Integer> perSkill = new ArrayList<Integer>();
// Pulling data from teju db
		String query="select skill_id from pos_skill where pos_code="+position_id;
		java.sql.PreparedStatement stmt;
		dbConnection= (Connection) con.setConnection();
		try {
			stmt = dbConnection.prepareStatement(query);
		
	    ResultSet rs = stmt.executeQuery();
	    while(rs.next())
	    {
	    	posSkill.add(rs.getInt(1));
	    		
	    }//while
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

// Calling webservice from abhi service
		

		ServTMSystemToTejuStub tstub;
		try {
			tstub= new ServTMSystemToTejuStub();
			GetSkillSet gset= new GetSkillSet();
			gset.setPer_ID(person_id);
			GetSkillSetResponse gresp = tstub.getSkillSet(gset);
			//result.add(gresp.get_return());
			int [] k= new int[gresp.get_return().length];
			k=gresp.get_return();
			for (int i=0;i<gresp.get_return().length;i++)
			{
				perSkill.add(k[i]);
			}
		
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
// comparing arraylists to find missing skills
		
		for(int i=0; i< posSkill.size();i++)
		{
			if (!perSkill.contains(posSkill.get(i)))
			{
				result.add(posSkill.get(i));
			}	
		}	
		
// end of webservice call
		return result;
	}
	
	
	public List<String> recommendCourse(int person_id, int position_id)	
	{
		
		//division must be performed on the below tables
				List<String> result = new ArrayList<String>();
				List<Integer> posSkill = new ArrayList<Integer>();
				List<Integer> perSkill = new ArrayList<Integer>();
				List<Integer> temp = new ArrayList<Integer>();
		// Pulling data from teju db
				String query="select skill_id from pos_skill where pos_code="+position_id;
				java.sql.PreparedStatement stmt;
				dbConnection= (Connection) con.setConnection();
				try {
					stmt = dbConnection.prepareStatement(query);
				
			    ResultSet rs = stmt.executeQuery();
			    while(rs.next())
			    {
			    	posSkill.add(rs.getInt(1));
			    		
			    }//while
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 

		// Calling webservice from abhi service
				
				ServTMSystemToTejuStub tstub;
				try {
					tstub= new ServTMSystemToTejuStub();
					GetSkillSet gset= new GetSkillSet();
					gset.setPer_ID(person_id);
					GetSkillSetResponse gresp = tstub.getSkillSet(gset);
					//result.add(gresp.get_return());
					int [] k= new int[gresp.get_return().length];
					k=gresp.get_return();
					for (int i=0;i<gresp.get_return().length;i++)
					{
						perSkill.add(k[i]);
					}
				
				} catch (AxisFault e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		// comparing arraylists to find missing skills
				
				for(int i=0; i< posSkill.size();i++)
				{
					if (!perSkill.contains(posSkill.get(i)))
					{
						temp.add(posSkill.get(i));
					}	
				}	
				
		// end of webservice call
				
				for(int i=0; i< temp.size();i++)
				{
					query="insert into temp values("+temp.get(i)+")";
					try {
						stmt = dbConnection.prepareStatement(query);
						stmt.executeUpdate();
					} catch (SQLException e) {
						e.printStackTrace();
					}					
				}
		
				query="select knowledge_skill_code from knowledge_skills where knowledge_skill_code in (select * from temp)";
				
				try{
				stmt = dbConnection.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();
				while(rs.next())
			    {
			    	result.add(rs.getString(1));
			    		
			    }//while
				query="delete from temp";
				stmt = dbConnection.prepareStatement(query);
				rs = stmt.executeQuery();
				}
				 catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				return result;

	}
	
	
	public int[] completeCourse(int person_id, int courseID)
	{
		
		List<Integer> certList = new ArrayList<Integer>();
		List<Integer> perKSkill = new ArrayList<Integer>();
		
		String query="select knowledge_skill_code, cer_code from fills natural join knowledge_skills where c_code ="+courseID;
		java.sql.PreparedStatement stmt;
		dbConnection= (Connection) con.setConnection();
		try {
			stmt = dbConnection.prepareStatement(query);
		
	 // pulling data from from teju database
			ResultSet rs = stmt.executeQuery();
	    while(rs.next())
	    {
	    	perKSkill.add(rs.getInt(1));
	    	certList.add(rs.getInt(2));
	    		
	    }//while
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		// End of pulling data from from teju database	
		
		// invokeing webservice
		ServTMSystemToTejuStub tstub;
		int [] tempVar= new int [perKSkill.size()];
		try {
			tstub= new ServTMSystemToTejuStub();
			AddPerSkills apset= new AddPerSkills ();
			apset.setPerid(person_id);
			for(int i=0; i< tempVar.length;i++)
			{
				
				tempVar[i]=perKSkill.get(i);
			}
			apset.setSkillList(tempVar);
			AddPerSkillsResponse arsp = tstub.addPerSkills(apset);
			arsp.get_return();
			
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch(Exception e1){
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		// End of webservice
		
		return tempVar;
	}



}
