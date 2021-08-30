package com.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	
	@Autowired
	UserValidationService service;
	
	@RequestMapping(value="/")
	@ResponseBody
	public String sayHello() {
		return "Hello World";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginPage() {
		return "Login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String showWelcomePage(@RequestParam String name, 
			@RequestParam String pass,
			ModelMap model) {
		if(!service.isUserValid(name, pass)) {
			model.put("error","Invalid Credentials");
			return "Login";
		}
		model.put("name",name);
		model.put("pass",pass);
		return "Welcome";
	}
	
	
	
	
}
