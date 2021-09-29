package com.example.demo.exceptions;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	// MethodArgumentNotValidException
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomErrorDetails error = new CustomErrorDetails(new Date(), "From Method Argument Not Valid Exception",
				ex.getLocalizedMessage());

		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomErrorDetails error = new CustomErrorDetails(new Date(),
				"From Http Method REquest in GEH - method is not allowed", ex.getLocalizedMessage());

		return new ResponseEntity<Object>(error, HttpStatus.METHOD_NOT_ALLOWED);

	}
	
	//username not found exception
	@ExceptionHandler(UserNameNotFoundException.class)
	public final ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException ex, 
			WebRequest request ){
		CustomErrorDetails error = new CustomErrorDetails(new Date(),
				ex.getMessage(), request.getDescription(true));

		return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
	}
	
	//constrant violation exception
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintViolationException(
			ConstraintViolationException ex, WebRequest req
			){
		CustomErrorDetails error = new CustomErrorDetails(new Date(),
				ex.getMessage(), req.getDescription(true));

		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	
	
	
	

}
