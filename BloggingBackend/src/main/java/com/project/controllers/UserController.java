package com.project.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.LoginDto;
import com.project.dto.RegisterUserDTO;
import com.project.models.Users;
import com.project.repository.UserRepository;
import com.project.services.UserAuthService;
import com.project.util.EntitiyHawk;

@RestController
@RequestMapping("")
public class UserController extends EntitiyHawk {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserAuthService userAuthService;
	
	@Autowired
	private UserRepository userRepository;

	
	@PostMapping("/register")
	public ResponseEntity registerUser(@Valid @RequestBody RegisterUserDTO userDto, Errors errors) {
		logger.info("user in registeruser method is " + userDto);
		if(errors.hasErrors())
		{
			logger.warn("error found in the paramater" + errors);
			return genericError(errors.getFieldError().getField() + " " + errors.getFieldError().getDefaultMessage());
		}
		try {
			userAuthService.registerUser(userDto);
			return genericSuccess("User Registered");
		} catch (Exception e) {
			logger.info("Error while registering user : " + e);
		}
		return genericSuccess("User Can't be Registered");
	}

	@PostMapping("/login")
	public ResponseEntity loginUser(@RequestBody LoginDto userDto) throws Exception {
		try {
			String token = userAuthService.loginUser(userDto);
			return genericSuccess(token);
		}
		catch(UsernameNotFoundException e)
		{
			return genericError("Invalid Username or Password");
		}
	}
	
	@GetMapping("/getUser/{userID}")
	public ResponseEntity loginUser(@PathVariable Integer userID) throws Exception {
		Users user = userRepository.findById(userID).get();
		return genericSuccess(user);
	}

}
