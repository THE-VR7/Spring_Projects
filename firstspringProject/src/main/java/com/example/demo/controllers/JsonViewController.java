package com.example.demo.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.User;
import com.example.demo.entities.Views;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@Validated
@RequestMapping(value = "/users")
public class JsonViewController {
	
	@Autowired
	private UserService userService;


	@JsonView(Views.External.class)
	//get user by id - external
	@GetMapping("/external/{id}")
	public User getUserById(@PathVariable("id") @Min(1) Long id) {

		try {
			Optional<User> userOptional =  userService.getUserById(id);
			return userOptional.get();
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}

	}
	
	//get user by id - internal
	@JsonView(Views.Internal.class)
	@GetMapping("/internal/{id}")
	public User getUserById2(@PathVariable("id") @Min(1) Long id) {

		try {
			Optional<User> userOptional =  userService.getUserById(id);
			return userOptional.get();
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}

	}
	
}
