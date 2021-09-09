package com.mockito.mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SomeBusinessMockTest {
	
	@Mock
	DataService dataServiceMock;
	
	@InjectMocks
	SomeBusinessImpl businessImpl;

	@Test
	void testfindGreatest() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {1,2,3,4,5});
		int res = businessImpl.findGreatest();
		assertEquals(5,res);
	}
	
	@Test
	void testfindGreatest1() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {6,7,3,15});
		int res = businessImpl.findGreatest();
		assertEquals(15,res);
	}

}


