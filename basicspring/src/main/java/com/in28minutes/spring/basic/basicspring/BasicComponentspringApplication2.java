package com.in28minutes.spring.basic.basicspring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.in28minutes.spring.basic.basicspring.basic.BinarySearchImpl;
import com.in28minutes.spring.basic.basicspring.scope.PersonDao;
import com.in28minutes.spring.basic.componentscan.ComponentDao;

	// What are the beans
	// What are the dependencies of beans
	// How spring search for beans => no need in spring boot, it automatically searches

@SpringBootApplication
@ComponentScan("com.in28minutes.spring.basic.componentscan")
public class BasicComponentspringApplication2 {
	
	public static void main(String[] args) {
		
		 Logger LOGGER = 
				LoggerFactory.getLogger(BasicComponentspringApplication2.class);
//		BinarySearchImpl binarySearch = new BinarySearchImpl(new QuickSort());
		// Application Context
		
		ApplicationContext appcontext =  
				SpringApplication.run(BasicComponentspringApplication2.class, args);
		ComponentDao componentDao = appcontext.getBean(ComponentDao.class);
		
		LOGGER.info("{}",componentDao);
//		LOGGER.info("{}",componentDao.getJdbcConnection());
		
	}

}
