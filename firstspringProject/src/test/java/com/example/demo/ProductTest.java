package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entities.Product;

@SpringBootTest
class ProductTest {

	@Value("${firstspringproject.services.url}")
	private String baseUrl;
	
	@Test
	void testGetProducts() {
		System.out.println(baseUrl);
		RestTemplate restTemplate = new RestTemplate();
		Product forObject = restTemplate.getForObject(baseUrl+"1", Product.class);
		assertNotNull(forObject);
		assertEquals("dasdsad", forObject.getName());
	}

}
