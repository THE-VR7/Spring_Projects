package com.mockito.mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

class SomeBusinessMockAnnotationTest {
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testfindGreatest() {
		DataService dataServiceMock = mock(DataService.class);
		
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {1,2,3,4,5});
		
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceMock);
		int res = businessImpl.findGreatest();
		assertEquals(5,res);
	}
	
	@Test
	void testfindGreatest1() {
		DataService dataServiceMock = mock(DataService.class);
		
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {6,7,3,15});
		
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceMock);
		int res = businessImpl.findGreatest();
		assertEquals(15,res);
	}

}


