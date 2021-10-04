package com.example.demo.dtos;

public class UserMsDto {

	private Long Id;
	private String username;
	private String emailaddress;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmailaddress() {
		return emailaddress;
	}
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	public UserMsDto() {
	
	}
	public UserMsDto(Long id, String username, String emailaddress) {
		super();
		Id = id;
		this.username = username;
		this.emailaddress = emailaddress;
	}
	
	
}
