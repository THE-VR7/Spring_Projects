package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entities.Product;
import com.example.demo.repos.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public List<Product> getProducts(){
		return (List<Product>) repository.findAll();
	}
	
	public Product getProduct(int id) {
		return repository.findById(id).get();
	}
	
	public Product createProduct(Product product) {
		return repository.save(product);
	}
	
	public Product updateProduct(Product product) {
		return repository.save(product);
	}
	
	public void deleteProduct(int id) {
		repository.deleteById(id);
	}
}
