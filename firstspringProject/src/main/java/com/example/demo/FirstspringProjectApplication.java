package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.demo.entities.Product;
import com.example.demo.repos.ProductRepository;
import com.example.demo.services.ProductService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
@EnableSwagger2
//@EnableCaching
public class FirstspringProjectApplication implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductService service;
	
	public static void main(String[] args) {
		SpringApplication.run(FirstspringProjectApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		
//		Product pr = new Product();
//		pr.setName("dasdsad");
//		pr.setDescription("dasdasddddddd a dasda");
//		pr.setPrice(12313);
//		repo.save(pr);
		
//		logger.info("All products are {}",service.getProducts());
		
	}

}
