package com.in28minutes.spring.basic.basicspring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.in28minutes.spring.basic.basicspring.basic.BinarySearchImpl;
import com.in28minutes.spring.basic.basicspring.cdi.SomeCDIBusiness;
import com.in28minutes.spring.basic.basicspring.scope.PersonDao;

	// What are the beans
	// What are the dependencies of beans
	//how spring search for beans => no need in spring boot, it automatically searches

//@SpringBootApplication
public class BasicCDIspringApplication {
	
	public static void main(String[] args) {
		
		 Logger LOGGER = 
				LoggerFactory.getLogger(BasicCDIspringApplication.class);
//		BinarySearchImpl binarySearch = new BinarySearchImpl(new QuickSort());
		// Application Context
		
		ApplicationContext applicationContext =  
				SpringApplication.run(BasicCDIspringApplication.class, args);
		
		SomeCDIBusiness someCdiBusiness = applicationContext.getBean(SomeCDIBusiness.class);
		LOGGER.info("{} dao-{}",someCdiBusiness,someCdiBusiness.getSomeCDIDAO());

	
	}

}
