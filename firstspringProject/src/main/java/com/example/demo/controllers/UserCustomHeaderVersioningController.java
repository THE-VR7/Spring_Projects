package com.example.demo.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dtos.UserDtoV1;
import com.example.demo.dtos.UserDtoV2;
import com.example.demo.dtos.UserMmDto;
import com.example.demo.entities.User;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("versioning/headers/users")
public class UserCustomHeaderVersioningController {

	// Autowire the UserService
	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	// getUserById - Version - V1
	@GetMapping(value = "/{id}", headers = "API-VERSION=1")
	public UserDtoV1 getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
		Optional<User> optionalUser = userService.getUserById(id);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User doesn't exists");
		}
		User user = optionalUser.get();
		UserDtoV1 dtov1 = modelMapper.map(user, UserDtoV1.class);
		return dtov1;
	}

	// getUserById - Version - V2
	@GetMapping(value = "/{id}", headers = "API-VERSION=2")
	public UserDtoV2 getUserById2(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
		Optional<User> optionalUser = userService.getUserById(id);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User doesn't exists");
		}
		User user = optionalUser.get();
		UserDtoV2 dtov2 = modelMapper.map(user, UserDtoV2.class);
		return dtov2;
	}

}
