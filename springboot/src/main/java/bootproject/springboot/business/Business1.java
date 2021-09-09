package bootproject.springboot.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bootproject.springboot.data.Dao1;

@Service
public class Business1 {
	
	@Autowired
	private Dao1 dao1;
	
	public String calculateSomething() {
		return dao1.retriveSomething();
	}
	
}
