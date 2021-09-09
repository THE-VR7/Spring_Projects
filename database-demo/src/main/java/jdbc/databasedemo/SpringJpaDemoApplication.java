package jdbc.databasedemo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jdbc.databasedemo.jdbc.entity.Person;
import jdbc.databasedemo.jpa.PersonJpaRepositoy;

@SpringBootApplication
public class SpringJpaDemoApplication implements CommandLineRunner {

	@Autowired
	PersonJpaRepositoy personJpa;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		logger.info(" user details are {}",personJpa.findById(10001));
//		
//		logger.info(" inserting new user {}",
//				personJpa.insert(new Person("jbhfghchhgjvj","Berlin",new Date())));
//		
//		logger.info(" updating 1003 {}",
//				personJpa.update(new Person(10003,"Peter","Berlin",new Date())));
//		
//		logger.info(" res are  {}",personJpa.delete(10002));
//		
//		logger.info("All users are {}",personJpa.findAll());
		
	}
	
	

}
