package com.in28minutes.spring.basic.basicspring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.in28minutes.spring.basic.basicspring.basic.BinarySearchImpl;
import com.in28minutes.spring.basic.basicspring.scope.PersonDao;

	// What are the beans
	// What are the dependencies of beans
	//how spring search for beans => no need in spring boot, it automatically searches

@SpringBootApplication
public class BasicScopespringApplication {
	
	public static void main(String[] args) {
		
		 Logger LOGGER = 
				LoggerFactory.getLogger(BasicScopespringApplication.class);
//		BinarySearchImpl binarySearch = new BinarySearchImpl(new QuickSort());
		// Application Context
		
		ApplicationContext applicationContext =  
				SpringApplication.run(BasicScopespringApplication.class, args);
		
		BinarySearchImpl binarySearch = 
				applicationContext.getBean(BinarySearchImpl.class);

		BinarySearchImpl binarySearch1 = 
				applicationContext.getBean(BinarySearchImpl.class);

		System.out.println(binarySearch);
		System.out.println(binarySearch1);
		
		int result = 
				binarySearch.search(new int[] { 12, 4, 6 }, 3);
		System.out.println(result);
//		PersonDao personDao = appcontext.getBean(PersonDao.class);
//		PersonDao personDao1 = appcontext.getBean(PersonDao.class);
//		
//		LOGGER.info("{}",personDao);
//		LOGGER.info("{}",personDao.getJdbcConnection());
//		
//		LOGGER.info("{}",personDao1);
//		LOGGER.info("{}",personDao1.getJdbcConnection());
		
	
		
	}

}
