package com.example.demo.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

//@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {
	
	@ExceptionHandler(UserNameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public CustomErrorDetails usernameNotFound(UserNameNotFoundException ex) {
		return new CustomErrorDetails(new Date(),
				"From REst controller advice", ex.getMessage());
	}
	
}
