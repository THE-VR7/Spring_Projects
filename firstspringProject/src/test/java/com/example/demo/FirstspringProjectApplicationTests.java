package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entities.Student;
import com.example.demo.repos.StudentRepository;
import com.example.demo.services.PaymentService;
import com.example.demo.services.PaymentServiceImpl;

@SpringBootTest
class FirstspringProjectApplicationTests {

	@Autowired
	PaymentServiceImpl paymentService;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Test
	void toTestDependencyInjection() {
		assertNotNull(paymentService.getPaymentDao());
	}
	
	@Test
	void testSaveStudents() {
		Student s = new Student();
		s.setId(11);
		s.setName("Vinee");
		s.setTestScore(25);
		
		studentRepo.save(s);
		
		Student savedStudent = studentRepo.findById(11L).get();
		assertNotNull(savedStudent);
		
	}

}
