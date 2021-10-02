package com.example.demo.controllers;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.LinkBuilder;
import org.springframework.hateoas.server.mvc.ControllerLinkRelationProvider;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.entities.Order;
import com.example.demo.entities.User;
import com.example.demo.exceptions.UserExistsException;
import com.example.demo.exceptions.UserNameNotFoundException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//Controller -
@RestController
@Validated
@RequestMapping(value = "/hateoas/users")
public class UserHateoasController {

	// Autowire the UserService
	@Autowired
	private UserService userService;
	
	// getAllUsers Method
	@ApiOperation(value = "Retrieve list of users")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public CollectionModel<User> getAllUsers() throws UserNotFoundException {
		List<User> allUsers =  userService.getAllUsers();
		for(User user : allUsers) {
			Long userId = user.getId();
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass())
							.slash(userId).withSelfRel();
			user.add(selfLink);
			
			// Relationship link with getAllOrders
			CollectionModel<Order> orders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class)
					.getAllOrders(userId);
			Link ordersLink = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
			user.add(ordersLink);
		}
		Link selfLinkusers = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
		CollectionModel<User> finalUsers = CollectionModel.of(allUsers,selfLinkusers);
		return finalUsers;
	}

	// getUserById
	@GetMapping("/{id}")
	public EntityModel<User> getUserById(@PathVariable("id") @Min(1) Long id) {

		try {
			Optional<User> userOptional =  userService.getUserById(id);
			User user =  userOptional.get();
			Long userId = user.getId();
			Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass())
					.slash(userId)
					.withSelfRel();
			user.add(selfLink);
			EntityModel<User> finalUser = EntityModel.of(user, selfLink);
			return finalUser;
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}