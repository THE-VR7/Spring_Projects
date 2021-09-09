package jdbc.databasedemo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jdbc.databasedemo.jdbc.entity.Person;

@Repository
public class PersonJdbcDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	class PersonRowMapper implements RowMapper{
		
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException{
			Person person = new Person();
			person.setId(rs.getInt("id"));
			person.setName(rs.getString(1));
			person.setLocation(rs.getString(2));
			person.setBirthDate(rs.getTimestamp(3));
			return person;
		}

		@Override
		public int[] getRowsForPaths(TreePath[] path) {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	
	//select * from PersonTable
	public List<Person> findAll(){
		return jdbcTemplate.query("select * from person",
				new BeanPropertyRowMapper<Person>(Person.class));
	}
	
	public Person findById(int id){
		return jdbcTemplate.queryForObject("select * from person where id=?",
				new Object[] {id},
				new BeanPropertyRowMapper<Person>(Person.class));
	}
	
	public int deleteById(int id){
		return jdbcTemplate.update("delete from person where id=?",
				new Object[] {id}
				);
	}
	
	public int insert(Person person){
		return jdbcTemplate.update
				("insert into person (ID, NAME, LOCATION, BIRTH_DATE ) "
						+ "VALUES(?,  ?, ?,?)",
				new Object[] {person.getId(),person.getName(),
						person.getLocation(),
						new Timestamp(person.getBirthDate().getTime())}
				);
	}
	
	public int update(Person person){
		return jdbcTemplate.update
				("update person set NAME = ?, LOCATION = ?, BIRTH_DATE = ?  "
						+ " where id = ?",
				new Object[] {person.getName(),
						person.getLocation(),
						new Timestamp(person.getBirthDate().getTime()),
						person.getId()}
				);
	}
	
	
	
}
