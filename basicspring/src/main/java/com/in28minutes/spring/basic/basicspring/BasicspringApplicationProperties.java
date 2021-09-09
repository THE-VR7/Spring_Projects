package com.in28minutes.spring.basic.basicspring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.in28minutes.spring.basic.basicspring.basic.BinarySearchImpl;
import com.in28minutes.spring.basic.basicspring.properties.Properties;


	// What are the beans
	// What are the dependencies of beans
	//how spring search for beans => no need in spring boot, it automatically searches

@Configuration
@ComponentScan
@PropertySource("classpath:app.properties")
public class BasicspringApplicationProperties { 
	public static void main(String[] args) {
		
//		BinarySearchImpl binarySearch = new BinarySearchImpl(new QuickSort());
		// Application Context
		
		try(AnnotationConfigApplicationContext applicationContext = 
				new AnnotationConfigApplicationContext(BasicspringApplicationProperties.class))
		{
			Properties property = 
					applicationContext.getBean(Properties.class);

			BinarySearchImpl binarySearch1 = 
					applicationContext.getBean(BinarySearchImpl.class);

			System.out.println(property.returnUrl());
//			System.out.println(binarySearch1);
			
//			int result = 
//					binarySearch.search(new int[] { 12, 4, 6 }, 3);
//			System.out.println(result);
	
		}
		
		
	}

}
