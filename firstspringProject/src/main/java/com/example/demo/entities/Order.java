package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="Orders")
public class Order extends RepresentationModel<Order> {
	
	@Id
	@GeneratedValue
	@JsonView(Views.Internal.class)
	private Long orderId;
	@JsonView(Views.Internal.class)
	private String orderDescription;
	
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDescription=" + orderDescription + "]";
	}

	// unless we specify to get the user, it should not get the user
	@ManyToOne(fetch = FetchType.LAZY)
	// it is not neccessary to provide the user when sending the data
	@JsonIgnore
	private User user;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderDescription() {
		return orderDescription;
	}

	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
