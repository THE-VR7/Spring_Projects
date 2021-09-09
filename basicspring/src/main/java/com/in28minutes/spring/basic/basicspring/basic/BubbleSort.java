package com.in28minutes.spring.basic.basicspring.basic;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("bubble")
public class BubbleSort implements SortAlgo {
	
	public int[] sort(int[] arr) {
		return arr;
	}
	
	public String toString() {
		return "This is the bubble sort algo";
	}

}
