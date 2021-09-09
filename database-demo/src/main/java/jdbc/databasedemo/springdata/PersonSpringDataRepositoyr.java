package jdbc.databasedemo.springdata;

import org.springframework.data.jpa.repository.JpaRepository;

import jdbc.databasedemo.jdbc.entity.Person;

public interface PersonSpringDataRepositoyr
				extends JpaRepository<Person, Integer>
{
	
	
}
