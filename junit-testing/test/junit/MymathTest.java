package junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MymathTest {
	@BeforeEach
	public void before() {
		System.out.println("Before every test");
	}
	
	@BeforeAll
	public static void beforeClass() {
		System.out.println("Before every class");
	}
	
	Mymath mymath = new Mymath();

	@Test
	void summethod() {
		System.out.println(6);
		assertEquals(6, mymath.sum(new int[] {1,2,3}));
	}
	
	@Test
	void summethod1() {
		System.out.println(1022);
		assertEquals(10, mymath.sum(new int[] {1,2,3,4}));
	}
	

}
