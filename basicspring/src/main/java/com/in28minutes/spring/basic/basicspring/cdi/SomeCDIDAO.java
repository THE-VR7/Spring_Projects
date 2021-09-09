package com.in28minutes.spring.basic.basicspring.cdi;

import javax.inject.Named;

//@Component
@Named
public class SomeCDIDAO {

	public int[] getData( ) {
		return new int[] {2,4,5,6,6};
	}
	
}
