package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.models.Users;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {

	@Query("Select a from Users a where a.userName IN ?1")
	Users findByUsername(String username);

	@Query("Select a from Users a where a.email IN ?1")
	Users findByUseremail(String email);
   
}
