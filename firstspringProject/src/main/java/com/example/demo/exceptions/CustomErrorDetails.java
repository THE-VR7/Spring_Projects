package com.example.demo.exceptions;

import java.util.Date;

//simple custom error details bean
public class CustomErrorDetails {
	private Date Timestamp;
	private String message;
	private String errorDetails;
	public Date getTimestamp() {
		return Timestamp;
	}
	public void setTimestamp(Date timestamp) {
		Timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorDetails() {
		return errorDetails;
	}
	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}
	public CustomErrorDetails(Date timestamp, String message, String errorDetails) {
		super();
		Timestamp = timestamp;
		this.message = message;
		this.errorDetails = errorDetails;
	}
	
	
}
