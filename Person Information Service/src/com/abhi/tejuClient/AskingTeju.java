package com.abhi.tejuClient;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.AxisFault;

import com.abhi.tejuClient.ServPSystemToAbhiStub.GetPositionSkillSet;
import com.abhi.tejuClient.ServPSystemToAbhiStub.GetPositionSkillSetResponse;

public class AskingTeju {
	
	public List<Integer> showResultSkill(int pos_id)
	{
		List<Integer> result = new ArrayList<Integer>();
		
		try {
			ServPSystemToAbhiStub stub = new ServPSystemToAbhiStub();
			GetPositionSkillSet gpset = new GetPositionSkillSet();
			gpset.setPosition_id(pos_id);
			GetPositionSkillSetResponse gpresp = stub.getPositionSkillSet(gpset);
			
			int [] k= new int[gpresp.get_return().length];
			k=gpresp.get_return();
			for (int i=0;i<gpresp.get_return().length;i++)
			{
				result.add(k[i]);
			}
			
			
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	

}
