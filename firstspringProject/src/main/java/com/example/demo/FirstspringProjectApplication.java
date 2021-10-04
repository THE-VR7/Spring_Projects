package com.example.demo;


import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.LocaleResolver;


import com.example.demo.services.ProductService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
	
	@Bean
	public LocaleResolver localResolver() {
		AcceptHeaderLocaleResolver localResolver = new AcceptHeaderLocaleResolver();
		localResolver.setDefaultLocale(Locale.US);
		return localResolver;
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("message");
		return messageSource;
	}

}
