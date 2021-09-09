package com.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Value(value = "${springjms.myqueue}")
	private String queue;
	
	public void send(String message) {
		MessageCreator mc = s -> s.createTextMessage("Hello to the spring jms");
		jmsTemplate.send(queue, mc);
	}
	
	
}
