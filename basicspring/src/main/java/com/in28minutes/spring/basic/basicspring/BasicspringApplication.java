package com.in28minutes.spring.basic.basicspring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.in28minutes.spring.basic.basicspring.basic.BinarySearchImpl;


	// What are the beans
	// What are the dependencies of beans
	//how spring search for beans => no need in spring boot, it automatically searches

@Configuration
@ComponentScan
public class BasicspringApplication {
	
	public static void main(String[] args) {
		
//		BinarySearchImpl binarySearch = new BinarySearchImpl(new QuickSort());
		// Application Context
		
		try(AnnotationConfigApplicationContext applicationContext = 
				new AnnotationConfigApplicationContext(BasicspringApplication.class))
		{
			BinarySearchImpl binarySearch = 
					applicationContext.getBean(BinarySearchImpl.class);

			BinarySearchImpl binarySearch1 = 
					applicationContext.getBean(BinarySearchImpl.class);

			System.out.println(binarySearch);
			System.out.println(binarySearch1);
			
			int result = 
					binarySearch.search(new int[] { 12, 4, 6 }, 3);
			System.out.println(result);
	
		}
		
		
	}

}
