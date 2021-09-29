package com.example.demo.repos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.User;

//Repository
@Repository
public interface UserRepository  extends JpaRepository<User, Long>{

	User findByUsername(String username);
}