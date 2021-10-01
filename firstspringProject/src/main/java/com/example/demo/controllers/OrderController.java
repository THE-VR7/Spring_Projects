package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/users")
public class OrderController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping(value = "/{userId}/orders")
	public List<Order> getAllOrders(@PathVariable long userId) throws UserNotFoundException{
		Optional<User> user = userRepository.findById(userId);
		if(!user.isPresent())
		{
			throw new UserNotFoundException("User not found");
		}
		return user.get().getOrders();
		
	}
	
	@PostMapping(value = "{userId}/orders")
	public Order createOrder(@PathVariable long userId, @RequestBody Order order) throws UserNotFoundException
	{
		Optional<User> optionaluser = userRepository.findById(userId);
		if(!optionaluser.isPresent())
		{
			throw new UserNotFoundException("User not found");
		}
		User user = optionaluser.get();
		order.setUser(user);
		return orderRepository.save(order);
	}
	
	@GetMapping("/{userId}/orders/{orderId}")
	public Order getOrderbyOrderId(@PathVariable Long userId, @PathVariable Long orderId) throws Exception {
		Optional<User> optionaluser = userRepository.findById(userId);
		if(!optionaluser.isPresent())
		{
			throw new UserNotFoundException("User not found");
		}
		User user = optionaluser.get();
		
		Optional<Order> order = user.getOrders().stream().filter(ord -> ord.getOrderId() == orderId).findFirst();
		if(!order.isPresent())
		{
			throw new Exception("Order is not present");
		}
		return order.get();
	}
	
	
}









