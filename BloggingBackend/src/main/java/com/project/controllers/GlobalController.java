package com.project.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.PostDTO;
import com.project.dto.UpdatePostDTO;
import com.project.models.Posts;
import com.project.models.Users;
import com.project.repository.PostRepository;
import com.project.repository.UserRepository;
import com.project.services.UserAuthService;
import com.project.util.EntitiyHawk;
import com.project.util.PostMapper;

import io.jsonwebtoken.Claims;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@RestController
@RequestMapping("/api")
public class GlobalController extends EntitiyHawk {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalController.class);
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserAuthService userAuthService;
	
	@Autowired
	EntitiyHawk entitiyHawk;
	
	//publish post
	@PostMapping("/publish")
	public ResponseEntity publishPost(@Valid @RequestBody PostDTO postDto, Errors errors)
	{
		if(errors.hasErrors())
		{
			logger.warn("error found in the paramater" + errors);
			return genericError(errors.getFieldError().getField() +" " + errors.getFieldError().getDefaultMessage());
		}
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logger.info("auth details are : "  + user.getUsername());
		Posts post = PostMapper.postDtoToPosts(postDto);
		Users curruser = userRepository.findByUsername(user.getUsername());
		post.setPublishedBy(curruser);
		postRepository.save(post);
		return genericSuccess("Published");
	}
	
	@GetMapping("/getPost")
	public ResponseEntity getPosts()
	{
		List<Posts> posts = postRepository.findAll();
		logger.info("Posts are : " + posts);
		List<Map> ls = new ArrayList<>();
		for(Posts curr : posts)
		{
			ls.add(PostMapper.postDetailsToMap(curr));
		}
		return genericSuccess(ls);
	}
	
	@GetMapping("/getPostCount")
	public ResponseEntity getPostCount()
	{
		int count = (int) postRepository.count();
		return genericSuccess(count);
	}
	
	@GetMapping("/getPostByUser/{userId}")
	public ResponseEntity getPostByUser(@PathVariable long userId)
	{
		Users user = userRepository.findById((int) userId).get();
		List<Posts> posts = postRepository.findByUser(user);
		if(posts == null || posts.size() == 0)
		{
			return genericError("No posts by user Id " + userId);
		}
		List<Map> ls = new ArrayList<>();
		for(Posts curr : posts)
		{
			ls.add(PostMapper.postDetailsToMap(curr));
		}
		return genericSuccess(ls);
	}
	
	@PostMapping("/updatePost")
	public ResponseEntity updatePost(@RequestBody UpdatePostDTO postDto)
	{
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Users curruser = userRepository.findByUsername(user.getUsername());
		Posts post = curruser.postsList.stream().filter(p -> p.getPostId() == postDto.getPost_id()).findAny().orElse(null);
		if(post != null)
		{
			post.setPostBody(postDto.getBody());
			post.setPostTitle(postDto.getTitle());
			post.setUpdatedOn(new Date());
			postRepository.save(post);
			return genericSuccess("Post updated");
		}
		return genericError("Post Can't be Updated");
	}
	
	@GetMapping("/getPost/{postID}")
	public ResponseEntity getPostByID(@PathVariable long postID)
	{
		Optional<Posts> posts = postRepository.findById((int) postID);
		if(posts.isEmpty())
		{
			return genericError("Post Not Found");
		}
		return genericSuccess(PostMapper.postDetailsToMap(posts.get()));
//		return genericSuccess(posts.get());
	}
	
	@GetMapping("/deletePost/{postID}")
	public ResponseEntity deletePost(@PathVariable long postID)
	{
		logger.info("Entered delete post method in global controller");
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Users curruser = userRepository.findByUsername(user.getUsername());
		Posts post = curruser.postsList.stream().filter(p -> p.getPostId() == postID).findAny().orElse(null);
		logger.info("post found is " + post + " current logged in user is " + curruser);
		if(post != null)
		{
			curruser.removePost(post);
			post.setIsDeleted(true);
			postRepository.save(post);
			return genericSuccess("Post Deleted");
		}
		return genericError("Post Can't be deleted");
	}
}

///api/getPostCount - GET - Returns the count of the number of posts in the blog ("data" key genericSuccess(Object data)).
///api/getPostByUser/{userId} - GET - Returns all the posts published by a particular user.
///api/updatePost - POST - Checks the received DTO for inputs and ensures that a post is updated only by the author of the post. Users cannot update posts authored by others.
///api/getPost/{postID} - GET - Returns a post as per the post ID specified.
///api/deletePost/{postID} - GET - Deletes a post as per the post ID specified. Users cannot delete posts authored by others.