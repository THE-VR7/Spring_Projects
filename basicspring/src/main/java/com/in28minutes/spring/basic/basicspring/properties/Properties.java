package com.in28minutes.spring.basic.basicspring.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Properties {

	// property file
	@Value("${external.service.url}")
	private String url;
	
	public String returnUrl() {
		return this.url;
	}
}
