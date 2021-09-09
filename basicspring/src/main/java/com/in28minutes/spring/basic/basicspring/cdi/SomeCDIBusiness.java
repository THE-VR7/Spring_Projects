package com.in28minutes.spring.basic.basicspring.cdi;

import javax.inject.Inject;
import javax.inject.Named;

//@Component
@Named
public class SomeCDIBusiness {
	@Inject
	SomeCDIDAO someCDIDAO;

	public SomeCDIDAO getSomeCDIDAO() {
		return someCDIDAO;
	}

	public void setSomeCDIDAO(SomeCDIDAO someCDIDAO) {
		this.someCDIDAO = someCDIDAO;
	}
	
	public int findGreatest() {
		int greatest = -1;
		int[] data = someCDIDAO.getData();
		
		for(int val : data) {
			greatest = Math.max(greatest, val);
		}
		return greatest;
	}

}
