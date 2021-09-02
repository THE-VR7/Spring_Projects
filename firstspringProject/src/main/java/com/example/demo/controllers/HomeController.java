package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@RequestMapping(value="/")
		public String hello() {
			return "hello";
		}
		
		@RequestMapping(value="/sendData")
		public ModelAndView sendDate() {
			ModelAndView mav = new ModelAndView("data");
			mav.addObject("message","Take up one idea");
			return mav;
		}
}

