package com.abhi.connect.teju;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import org.apache.axis2.AxisFault;

import com.abhi.connect.teju.ServTMSystemToTejuStub.GetSkillSet;
import com.abhi.connect.teju.ServTMSystemToTejuStub.GetSkillSetResponse;


public class AskingAbhi {
	
	public List<Integer> showResult(int person_id)
	{
		List<Integer> result=new ArrayList<Integer>();
		
		// Stub object 
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
