package bootproject.springboot.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bootproject.springboot.data.Dao2;

@Service
public class Business2 {
	
	@Autowired
	private Dao2 dao2;
	
	public String calculateSomething() {
		return dao2.retriveSomething();
	}
	
}
