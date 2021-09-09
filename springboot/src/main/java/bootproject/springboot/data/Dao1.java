package bootproject.springboot.data;

import org.springframework.stereotype.Repository;

import bootproject.springboot.aspect.TrackTime;

@Repository
public class Dao1 {
	
	@TrackTime
	public String retriveSomething() {
		return "The new string";
	}
}
