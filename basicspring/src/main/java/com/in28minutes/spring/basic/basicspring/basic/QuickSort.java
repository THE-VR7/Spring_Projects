package com.in28minutes.spring.basic.basicspring.basic;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier("quick")
public class QuickSort implements SortAlgo {
	
	public int[] sort(int[] arr) {
		
		return arr;
	}
	
	public String toString() {
		return "This is the quick sort algo";
	}

}
