package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.UserMsDto;
import com.example.demo.entities.User;
import com.example.demo.mappers.UserMapper;
import com.example.demo.repos.UserRepository;

@RestController
@RequestMapping("/mapstruct/users")
public class UserMapStructController {

	@Autowired
	public UserRepository userRepository;
	
//	We must remember not to make the injected bean private! This is because MapStruct has to access the object in the generated implementation class.

	@Autowired
	public UserMapper userMapper;
	
	@GetMapping
	public List<UserMsDto> getAllUsersDto(){
		return userMapper.usersTousersMsDtos(userRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public UserMsDto getUserById(@PathVariable Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		User user = userOptional.get();
		return userMapper.userTouserMsDto(user);
	}
	
	
}
