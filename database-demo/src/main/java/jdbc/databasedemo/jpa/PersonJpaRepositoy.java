package jdbc.databasedemo.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import jdbc.databasedemo.jdbc.entity.Person;

//repository
//transaction
@Repository
@Transactional
public class PersonJpaRepositoy {

	//connection
	@PersistenceContext
	EntityManager entityManager;
	
	public Person findById(int id){
		return entityManager.find(Person.class, id);				
	}
	
	public Person update(Person person) {
		return entityManager.merge(person);
	}
	
	public Person insert(Person person) {
		return entityManager.merge(person);
	}
	
	public boolean delete(int id) {
		Person person = findById(id);
		 entityManager.remove(person);
		 return true;
	}
	
	public List<Person> findAll(){
		TypedQuery<Person> namedQuery =  
				entityManager.createNamedQuery("find_all_persons",Person.class);
		return namedQuery.getResultList();
	}
	
}
