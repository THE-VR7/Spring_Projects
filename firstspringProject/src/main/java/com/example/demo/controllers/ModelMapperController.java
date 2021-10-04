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

import com.example.demo.dtos.UserMmDto;
import com.example.demo.entities.User;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/modelmapper/users")
public class ModelMapperController {

	// Autowire the UserService
		@Autowired
		private UserService userService;
	
		@Autowired
		private ModelMapper modelMapper;
		
	// getUserById
		@GetMapping("/{id}")
		public UserMmDto getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
			 Optional<User> optionalUser = userService.getUserById(id);
			 if(!optionalUser.isPresent())
			 {
				 throw new UserNotFoundException("User doesn't exists");
			 }
			 User user = optionalUser.get();
			 UserMmDto dto = modelMapper.map(user, UserMmDto.class);
			 return dto;
		}
}
