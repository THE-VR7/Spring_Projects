package com.example.demo.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.SendResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entities.Product;
import com.example.demo.repos.ProductRepository;
import com.example.demo.services.ProductService;

import io.swagger.annotations.ApiOperation;

@RestController
public class Productcontroller {

	@Autowired
	ProductService service;
	private Product product;
	
	@ApiOperation(value = "Retrives all the products",
			notes = "A list of products",
			response = Product.class,
			responseContainer = "List",
			produces = "application/json"
			)
	@RequestMapping(value = "/products",method = RequestMethod.GET)
	public ModelAndView getProducts(){
		ModelAndView mav = new ModelAndView("products");
		List<Product> products = service.getProducts();
		mav.addObject("products", products);
		return mav;
	}
	
	@RequestMapping(value = "/allproducts",method = RequestMethod.GET)
	public List<Product> findAll(){
		List<Product> products = service.getProducts();
		return products;
	}
	
	@RequestMapping(value = "/products/{id}",method = RequestMethod.GET)
	public ModelAndView getProduct(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("product");
		Product product = service.getProduct(id);
		mav.addObject("product", product);
		return mav;
	}
	
	@RequestMapping(value = "/products/serialize/{id}",method = RequestMethod.GET)
	@Transactional(readOnly = true)
	@Cacheable("product-cache")
	public Product getSerializableProduct(@PathVariable("id") int id) {
		return service.getProduct(id);
	}
	
	@RequestMapping(value = "/addproduct",method = RequestMethod.GET)
	public ModelAndView getProductForm() {
		ModelAndView mav = new ModelAndView("AddProduct");
		Product product = new Product();
		mav.addObject("product", product);
		return mav;
	}
	
	@Transactional
	@RequestMapping(value = "/saveproduct",method = RequestMethod.POST)
	public void createProduct(@ModelAttribute Product product,HttpServletResponse res) throws IOException {
		System.out.println(product);
		service.createProduct(product);
		res.sendRedirect("http://localhost:8080/products");
	}
	
	@RequestMapping(value = "/saveproduct1",method = RequestMethod.POST)
	public Product createProduct1(@Valid @ModelAttribute Product product,HttpServletResponse res) throws IOException {
		System.out.println(product);
		return service.createProduct(product);
	}
	
	@Transactional
	@RequestMapping(value = "/products/",method = RequestMethod.PUT)
	public Product updateProduct(@RequestBody Product product) {
		return service.updateProduct(product);
	}
	
	@CacheEvict("product-cache")
	@RequestMapping(value = "/products/{id}",method = RequestMethod.DELETE)
	public void deleteProduct(@PathVariable("id") int id) {
		service.deleteProduct(id);
	}
}
