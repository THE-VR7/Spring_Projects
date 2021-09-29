package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entities.Product;
import com.example.demo.exceptions.ProductNotFound;
import com.example.demo.repos.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public List<Product> getProducts(){
		return (List<Product>) repository.findAll();
	}
	
	public Product getProduct(int id) throws ProductNotFound {
		Product product;
		try {
			product = repository.findById(id).get();
		}
		catch(Exception e) {
			throw new ProductNotFound("No available Product is there with given Id");
		}
		return product;
	}
	
	public Product createProduct(Product product) throws ProductNotFound {
		Product product2;
		try {
			product2 = repository.save(product);
		}
		catch(Exception e) {
			throw new ProductNotFound("Sorry! Can not Create a Product.");
		}
		return product2;
	}
	
	public Product updateProduct(Product product) throws ProductNotFound {
		Product product2;
		try {
			product2 = repository.save(product);
		}
		catch(Exception e) {
			throw new ProductNotFound("Sorry! Can not Update the Product.");
		}
		return product2;
	}
	
	public void deleteProduct(int id) throws ProductNotFound {
		try {
			repository.deleteById(id);
			}
		catch(Exception e) {
			throw new ProductNotFound("Sorry! Can not Delete the Product.");
		}
	}
}
