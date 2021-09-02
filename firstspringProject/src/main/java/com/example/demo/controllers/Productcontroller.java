package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Product;
import com.example.demo.repos.ProductRepository;
import com.example.demo.services.ProductService;

@RestController
public class Productcontroller {

	@Autowired
	ProductService service;
	
	@RequestMapping(value = "/products",method = RequestMethod.GET)
	public List<Product> getProducts(){
		return service.getProducts();
	}
	
	@RequestMapping(value = "/products/{id}",method = RequestMethod.GET)
	public Product getProduct(@PathVariable("id") int id) {
		return service.getProduct(id);
	}
	
	@Transactional
	@RequestMapping(value = "/products/",method = RequestMethod.POST)
	public Product createProduct(@RequestBody Product product) {
		return service.createProduct(product);
	}
	
	@Transactional
	@RequestMapping(value = "/products/",method = RequestMethod.PUT)
	public Product updateProduct(@RequestBody Product product) {
		return service.updateProduct(product);
	}
	
	@RequestMapping(value = "/products/{id}",method = RequestMethod.DELETE)
	public void deleteProduct(@PathVariable("id") int id) {
		service.deleteProduct(id);
	}
}
