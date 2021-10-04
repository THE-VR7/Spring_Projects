package com.example.demo.controllers;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.entities.User;
import com.example.demo.exceptions.UserExistsException;
import com.example.demo.exceptions.UserNameNotFoundException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.services.UserService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//Controller -
@RestController
@Validated
@RequestMapping(value = "/jackson/users")
public class UserMappingController {

	// Autowire the UserService
	@Autowired
	private UserService userService;
	
	// getUserById
	@GetMapping("/{id}")
	public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) Long id) {

		try {
			Optional<User> userOptional =  userService.getUserById(id);
			User user =  userOptional.get();
			Set<String> fields = new HashSet<String>();
			fields.add("firstname");
			fields.add("username");
			fields.add("ssn");
			FilterProvider filterProvide = new SimpleFilterProvider()
					.addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			MappingJacksonValue mapper = new MappingJacksonValue(user);
			mapper.setFilters(filterProvide);
			return mapper;
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}

	}
	
	
	// getUserById
		@GetMapping("/dynamic/{id}")
		public MappingJacksonValue getUserById2(@PathVariable("id") @Min(1) Long id,
				@RequestParam Set<String> fields) {

			try {
				Optional<User> userOptional =  userService.getUserById(id);
				User user =  userOptional.get();
				FilterProvider filterProvide = new SimpleFilterProvider()
						.addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields));
				MappingJacksonValue mapper = new MappingJacksonValue(user);
				mapper.setFilters(filterProvide);
				return mapper;
			} catch (UserNotFoundException ex) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
			}

		}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}