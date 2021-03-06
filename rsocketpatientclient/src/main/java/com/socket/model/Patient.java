package com.socket.model;

public class Patient {
	
	private String firstName;
	private String lastName;
	private String SSN;
	public Patient(String firstName, String lastName, String sSN) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		SSN = sSN;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSSN() {
		return SSN;
	}
	public void setSSN(String sSN) {
		SSN = sSN;
	}
	@Override
	public String toString() {
		return "Patient [firstName=" + firstName + ", lastName=" + lastName + ", SSN=" + SSN + "]";
	}
	
	
	
}
