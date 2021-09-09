package com.in28minutes.spring.basic.basicspring.xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

public class XmlPersonDao {
	
	public XmlJdbcConnection getJdbcConnection() {
		return jdbcConnection;
	}

	public void setJdbcConnection(XmlJdbcConnection jdbcConnection) {
		this.jdbcConnection = jdbcConnection;
	}

	XmlJdbcConnection jdbcConnection;
	
}
