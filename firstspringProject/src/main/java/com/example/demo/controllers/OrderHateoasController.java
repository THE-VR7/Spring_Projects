package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Order;
import com.example.demo.entities.User;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repos.OrderRepository;
import com.example.demo.repos.UserRepository;


@RestController
@RequestMapping(value = "/hateoas/users")
public class OrderHateoasController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping(value = "/{userId}/orders")
	public CollectionModel<Order> getAllOrders(@PathVariable long userId) throws UserNotFoundException{
		Optional<User> user = userRepository.findById(userId);
		if(!user.isPresent())
		{
			throw new UserNotFoundException("User not found");
		}
		List<Order> orders =  user.get().getOrders();
		CollectionModel<Order> finalOrders = CollectionModel.of(orders);
		return finalOrders;
	}
	
	
}









