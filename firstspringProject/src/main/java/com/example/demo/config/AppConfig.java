package com.example.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.mappers.UserMapper;

@Configuration
public class AppConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	
}
