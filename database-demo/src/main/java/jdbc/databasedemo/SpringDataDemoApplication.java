package jdbc.databasedemo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jdbc.databasedemo.jdbc.entity.Person;
import jdbc.databasedemo.springdata.PersonSpringDataRepositoyr;

@SpringBootApplication
public class SpringDataDemoApplication implements CommandLineRunner {

	@Autowired
	PersonSpringDataRepositoyr personJpa;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		logger.info(" user details are {}",personJpa.findById(10001));
		
		logger.info(" inserting new user {}",
				personJpa.save(new Person("jbhfghchhgjvj","Berlin",new Date())));
		
		logger.info(" updating 1003 {}",
				personJpa.save(new Person(10003,"Peter","Berlin",new Date())));
		
		personJpa.deleteById(10002);
		
		logger.info("All users are {}",personJpa.findAll());
		
	}
	
	

}
