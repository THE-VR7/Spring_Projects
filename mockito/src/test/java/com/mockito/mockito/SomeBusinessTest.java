package com.mockito.mockito;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

class SomeBusinessStubTest {
	SomeBusinessImpl businessImpl = new SomeBusinessImpl(new DataServiceStub());
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testfindGreatest() {
		int res = businessImpl.findGreatest();
		assertEquals(44,res);
	}

}

class DataServiceStub implements DataService{

	@Override
	public int[] retrieveAllData() {
		return new int[] {24,6,15,44};
	}
	
}
