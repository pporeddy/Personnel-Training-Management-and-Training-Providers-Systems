package com.prithvi.recivefromall;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.AxisFault;

import com.prithvi.connectfromteju.SendtoPrithviFromTjStub;
import com.prithvi.connectfromteju.SendtoPrithviFromTjStub.GetSkillMenuForMaxPay;
import com.prithvi.connectfromteju.SendtoPrithviFromTjStub.GetSkillMenuForMaxPayResponse;

import com.prithvi.connectfromabhi.SendToPrithviStub;
import com.prithvi.connectfromabhi.SendToPrithviStub.PossiblePosition;
import com.prithvi.connectfromabhi.SendToPrithviStub.PossiblePositionResponse;


public class TrainingProviderSystem {
	
	public List<Integer> hotCources(int company_id, int person_id)
	{
		List<Integer> result = new ArrayList<Integer>();
		int[] abhi;
		int[] tj;
		
		//  pulling data from teju service
		try {
			SendtoPrithviFromTjStub tjstub = new SendtoPrithviFromTjStub();
			GetSkillMenuForMaxPay gpay = new GetSkillMenuForMaxPay();
			gpay.setCompany_id(company_id);
			GetSkillMenuForMaxPayResponse gresp = new GetSkillMenuForMaxPayResponse();
			gresp = tjstub.getSkillMenuForMaxPay(gpay);
			tj = gresp.get_return();
			// End of pulling data from teju
			
			//  pulling data from abhi service
			SendToPrithviStub astub = new SendToPrithviStub();
			PossiblePosition ppay = new PossiblePosition();
			ppay.setPid(person_id);
			PossiblePositionResponse presp = new PossiblePositionResponse();
			presp = astub.possiblePosition(ppay);
			abhi = presp.get_return();	
			
			// End of pulling data from abhi
			
			// Finding missing skills for max pay
			
			for(int i=0; i<tj.length;i++)
			{
				int c =0;
				
				for(int j=0; j<abhi.length;j++)
				{
					if(tj[i]==abhi[j])
					{
					c++;}
				}
					if (c==0)
						result.add(tj[i]);
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
