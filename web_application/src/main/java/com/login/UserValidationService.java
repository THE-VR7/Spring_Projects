package com.login;

import org.springframework.stereotype.Service;

@Service
public class UserValidationService {

	public boolean isUserValid(String user,String pass) {
		if(user.equalsIgnoreCase("vineet") && pass.equalsIgnoreCase("1234"))
			return true;
		return false;
	}
}
