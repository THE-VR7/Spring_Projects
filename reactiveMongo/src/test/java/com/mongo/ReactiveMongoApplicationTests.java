package com.mongo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Controller;

import com.mongo.collections.Product;
import com.mongo.controller.ProductController;
import com.mongo.repos.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class ReactiveMongoApplicationTests {

	@Autowired
	ProductController controller;

	@MockBean
	ProductRepository repo;

	@Test
	void testAddProduct() {
		Product product = new Product(null, "vineet", "A big gangafsads", 121313d);
		Product savedProduct = new Product("asdsads", "vineet", "A big gangafsads", 121313d);

		when(repo.save(product)).thenReturn(Mono.just(savedProduct));

		StepVerifier.create(controller.addProduct(product))
			.assertNext(p->{
				assertNotNull(p);
				assertNotNull(p.getId());
				assertEquals("asdsads", p.getId());
			})
			.expectComplete()
			.verify();
	}
	
	@Test
	void testGetProducts() {
		when(repo.findAll())
			.thenReturn(Flux.just(new Product("12", "vineet", "A big gangafsads", 121313d),
					new Product("13", "vinsadasdeet", "A big gangafsads", 121313d),
					new Product("14", "vineet", "A big gangafsads", 121313d)
					));

		StepVerifier.create(controller.getProducts())
			.expectNextCount(3)
			.expectComplete()
			.verify();
	}

}
