package com.in28minutes.spring.basic.basicspring.cdi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.configuration.injection.MockInjection;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.in28minutes.spring.basic.basicspring.BasicCDIspringApplication;
import com.in28minutes.spring.basic.basicspring.BasicspringApplication;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = BasicCDIspringApplication.class)
class SomeCdiBusinessTest {

	//Inject Mock
	@InjectMocks
	SomeCDIBusiness business;

	//Create Mock
	@Mock
	SomeCDIDAO businessDao;

	
	@Test
	void testBasicScenario() {
		
		//when businessDao.getData() return 
		
		Mockito.when(businessDao.getData()).thenReturn(new int[] {2,5});
		
		int res = business.findGreatest();
		assertEquals(5, res);
	}

}
