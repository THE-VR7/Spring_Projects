package com.example.demo.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
//		@RequestMapping(value="/")
//		public String hello() {
//			return "hello";
//		}
//		
//		@RequestMapping(value="/sendData")
//		public ModelAndView sendDate() {
//			ModelAndView mav = new ModelAndView("data");
//			mav.addObject("message","Take up one idea");
//			return mav;
//		}
		
		@GetMapping(value = "/hello-int")
		public String getMessagesInI18formate(@RequestHeader(name = "Accept-Language",
		required = false)  String locale) {
			return messageSource.getMessage("label.hello",null, new Locale(locale));
		}
		
		@GetMapping(value = "/hello-int2")
		public String getMessagesInI18formate2() {
			return messageSource.getMessage("label.hello",null, LocaleContextHolder.getLocale());
		}
}

