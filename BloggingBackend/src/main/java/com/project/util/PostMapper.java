package com.project.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;

import com.project.dto.PostDTO;
import com.project.models.Posts;

public class PostMapper {
    
    public static Map postDetailsToMap(Posts post) {
        Map map = new HashMap();
        map.put("post_id", post.getPostId().toString());
        map.put("title", post.getPostTitle());
        map.put("body", post.getPostBody());
        map.put("created_on",post.getCreatedOn());
        map.put("created_by", post.getPublishedBy().getUserName());
        map.put("last_updated", post.getUpdatedOn());
        return map;
    } 
    
    public static Posts postDtoToPosts(PostDTO dto)
    {
    	Posts post = new Posts();
    	post.setPostTitle(dto.getTitle());
    	post.setPostBody(dto.getBody());
    	return post;
    }
}
