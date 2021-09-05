package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.demo.entities.Product;
import com.example.demo.repos.ProductRepository;
import com.example.demo.services.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@WebMvcTest
class ProductRestcontrollerMvcTest {

	private static final String ProductstUrl = "/products/";

	private static final int PRICE = 1400;

	private static final String DESCRIPTION = "A macbook";

	private static final String NAME = "Macbook";

	private static final int Id = 1;

	@Autowired
	private MockMvc mockmvc;

	@MockBean
	private ProductService repository;

	@Test
	void testFindAll() throws Exception {
		Product product = buildProduct();
		List<Product> products = new ArrayList<Product>();
		products.add(product);
		when(repository.getProducts()).thenReturn(products);
		ObjectWriter prettyPrinter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		mockmvc.perform(get("/allproducts"))
		.andExpect(status().isOk())
		.andExpect(content().json(prettyPrinter.writeValueAsString(products)));
	}
	
	@Test
	public void testCreateProduct() throws JsonProcessingException, Exception {
		Product product = buildProduct();
		when(repository.createProduct(any())).thenReturn(product);
		ObjectWriter prettyPrinter = new ObjectMapper().writer().withDefaultPrettyPrinter();

		
		mockmvc.perform(post("/saveproduct1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(prettyPrinter.writeValueAsString(product)))
		.andExpect(status().isOk())
		.andExpect( content().json(prettyPrinter.writeValueAsString(product)));
	}
	
	@Test
	public void deleteProduct() throws Exception {
		doNothing().when(repository).deleteProduct(Id);
		mockmvc.perform(delete("/products/"+Id)).andExpect(status().isOk());
	}

	private Product buildProduct() {
		Product product = new Product();
		product.setId(Id);
		product.setName(NAME);
		product.setDescription(DESCRIPTION);
		product.setPrice(PRICE);
		return product;
	}

}
