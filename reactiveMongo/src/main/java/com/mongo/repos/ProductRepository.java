package com.mongo.repos;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.mongo.collections.Product;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
	

}
