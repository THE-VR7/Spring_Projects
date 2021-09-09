package com.in28minutes.spring.basic.basicspring.basic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.spring.basic.basicspring.BasicScopespringApplication;
import com.in28minutes.spring.basic.basicspring.BasicspringApplication;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BasicspringApplication.class)
class BinarySearchTest {

	@Autowired
	BinarySearchImpl binarySearch;

	@Test
	void testBasicScenario() {
		int res = binarySearch.search(new int[] {25,4,3,2}, 5);
		assertEquals(3, res);
	}

}
