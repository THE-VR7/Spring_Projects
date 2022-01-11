package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.models.Posts;
import com.project.models.Users;

@Repository
public interface PostRepository extends JpaRepository<Posts,Integer> {

	@Query("Select p from Posts p where p.publishedBy = ?1")
	List<Posts> findByUser(Users user);
}
