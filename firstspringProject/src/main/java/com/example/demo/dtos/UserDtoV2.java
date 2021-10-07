package com.example.demo.dtos;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.example.demo.entities.Order;
import com.example.demo.entities.Views;
import com.fasterxml.jackson.annotation.JsonView;

public class UserDtoV2 {
	@Id
	@GeneratedValue
	@JsonView(Views.External.class)
	private Long Id;
	
	@NotEmpty(message = "Username is a mandatory field. It can't be left empty")
	@Column(name="USER_NAME", length = 50, nullable = false, unique = true)
	@JsonView(Views.External.class)
	private String username;
	
	@Size(min = 2, message = "FirstName should have atleast 2 characters")
	@Column(name="FIRST_NAME", length = 50, nullable = false)
	@JsonView(Views.External.class)
	private String firstname;

	@Column(name="LAST_NAME", length = 50, nullable = false)
	@JsonView(Views.External.class)
	private String lastname;
	
	@Column(name="EMAIL_ADDRESS", length = 50, nullable = false)
	@JsonView(Views.External.class)
	private String email;
	
	@Column(name="ROLE", length = 50, nullable = false)
	@JsonView(Views.Internal.class)
	private String role;
	  
	@Column(name="SSN", length = 50, nullable = false , unique = true)
	@JsonView(Views.Internal.class)
	private String ssn;
	
//	Hibernate: alter table orders add constraint FKel9kyl84ego2otj2accfd8mr7 foreign key (user_id) references user (id)
	// this is done so that order knows about the foreign key, it is user id in order
	@OneToMany(mappedBy = "user")
	@JsonView(Views.Internal.class)
	private List<Order> orders;
	
	@Column(name = "Address")
	private String address;

	public UserDtoV2() {
	}

	public UserDtoV2(Long id,
			@NotEmpty(message = "Username is a mandatory field. It can't be left empty") String username,
			@Size(min = 2, message = "FirstName should have atleast 2 characters") String firstname, String lastname,
			String email, String role, String ssn, List<Order> orders, String address) {
		super();
		Id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
		this.orders = orders;
		this.address = address;
	}

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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	

}
