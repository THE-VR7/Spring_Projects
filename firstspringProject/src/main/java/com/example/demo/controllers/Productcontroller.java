package com.example.demo.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.SendResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entities.Product;
import com.example.demo.exceptions.ProductNotFound;
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
		ModelAndView mav;
		Product product;
		try {
			mav = new ModelAndView("product");
			product = service.getProduct(id);
			mav.addObject("product", product);
		} catch (ProductNotFound e) {
		mav = new ModelAndView("error");
		mav.addObject("error",e.getMessage());
		}
		return mav;
	}
	
//	@RequestMapping(value = "/products/serialize/{id}",method = RequestMethod.GET)
//	@Transactional(readOnly = true)
//	@Cacheable("product-cache")
//	public Product getSerializableProduct(@PathVariable("id") int id) {
//		return service.getProduct(id);
//	}
	
	@RequestMapping(value = "/addproduct",method = RequestMethod.GET)
	public ModelAndView getProductForm() {
		ModelAndView mav = new ModelAndView("AddProduct");
		Product product = new Product();
		mav.addObject("product", product);
		return mav;
	}
	
	@Transactional
	@RequestMapping(value = "/saveproduct",method = RequestMethod.POST)
	public ModelAndView createProduct(@ModelAttribute Product product,HttpServletResponse res) throws IOException {
		try {
			Product pr = service.createProduct(product);
			return getProduct(pr.getId());
		} catch (ProductNotFound e) {
			ModelAndView mav = new ModelAndView("error");
			mav.addObject("error",e.getMessage());
			return mav;	
			}
	}
	
	@RequestMapping(value = "/saveproduct1",method = RequestMethod.POST)
	public Product createProduct1(@Valid @ModelAttribute Product product,HttpServletResponse res) throws IOException {
		System.out.println(product);
		try {
			return service.createProduct(product);
		} catch (ProductNotFound e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Transactional
	@RequestMapping(value = "/products/",method = RequestMethod.PUT)
	public Product updateProduct(@RequestBody Product product) {
		try {
			return service.updateProduct(product);
		} catch (ProductNotFound e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@CacheEvict("product-cache")
	@RequestMapping(value = "/products/{id}",method = RequestMethod.DELETE)
	public ModelAndView deleteProduct(@PathVariable("id") int id) {
		ModelAndView mav;
		try {
			service.deleteProduct(id);
			return getProducts();
		} catch (ProductNotFound e) {
			mav = new ModelAndView("error");
			mav.addObject("error",e.getMessage());
			return mav;
		}
	}
}
