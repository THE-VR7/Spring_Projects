package com.project.services;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.controllers.UserController;
import com.project.dto.LoginDto;
import com.project.dto.RegisterUserDTO;
import com.project.models.Users;
import com.project.util.JWTUtils;
import com.project.repository.UserRepository;

@Service
public class UserAuthService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private JWTUtils jwtUtil;
	
	@Autowired
	private UserRepository userRepository;

	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepository.findByUsername(username);
		if(user == null)
			throw new UsernameNotFoundException("user with this username does not exist");
		return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),new ArrayList<>());
	
	}

	public void registerUser(RegisterUserDTO userDto) {
		Users user = new Users();
		user.setUserId(0);
		user.setUserName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		Users save = userRepository.save(user);
	}

	public String loginUser(LoginDto userDto) throws Exception {
		logger.info("Inside the loginuser method of USerAuthService");
//		authenticate(userDto.getEmail(),userDto.getPassword());
		Users currUser = userRepository.findByUseremail(userDto.getEmail());
		if(currUser == null)
			throw new UsernameNotFoundException("No User exist with the following email");
		logger.info("Found the currUser" + currUser);
		final String token = jwtUtil.CreateJWTToken(currUser);
		return token;
	}
	
	private void authenticate(String username, String password) throws Exception
	{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}
		catch(DisabledException e)
		{
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
