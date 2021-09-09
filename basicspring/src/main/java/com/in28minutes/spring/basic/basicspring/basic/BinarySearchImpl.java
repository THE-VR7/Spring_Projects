package com.in28minutes.spring.basic.basicspring.basic;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class BinarySearchImpl {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	@Qualifier("bubble")
	private SortAlgo sortAlgo;

	public int search(int[] arr,int numberToSearch) {
		
		// sort the algo
		int[] sorted = this.sortAlgo.sort(arr);	
		System.out.println(this.sortAlgo);
		
		return 3;
	}
	
	@PostConstruct
	public void postConstruct() {
		logger.info("Post construct");
	}
	
	@PreDestroy
	public void preDestroy() {
		logger.info("Pre destroy");
	}
	
}
