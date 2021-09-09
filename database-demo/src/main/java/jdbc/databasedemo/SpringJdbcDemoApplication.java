package jdbc.databasedemo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jdbc.databasedemo.jdbc.PersonJdbcDao;
import jdbc.databasedemo.jdbc.entity.Person;

@SpringBootApplication
public class SpringJdbcDemoApplication implements CommandLineRunner {

	@Autowired
	PersonJdbcDao personjdbc;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJdbcDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		logger.info("All users are {}",personjdbc.findAll());
//		logger.info(" user details are {}",personjdbc.findById(10001));;
//		
//		logger.info(" inserting new user {}",
//				personjdbc.insert(new Person(10004,"Tara","Berlin",new Date())));
//		
//		logger.info(" updating 1003 {}",
//				personjdbc.update(new Person(10003,"Peter","Berlin",new Date())));
//		
		
	}
	
	

}
