package com.batch;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class Reader implements ItemReader<String> {

	private String[] arr = new String[] {"java","angualr","spring-boot"};
	private int count;
	
	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		System.out.println("Inside read method");
		if(count<arr.length) {
			return arr[count++];
		}
		else
		{
			count=0;
		}
		return null;
	}

}
