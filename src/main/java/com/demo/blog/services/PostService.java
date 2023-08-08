package com.demo.blog.services;

import com.demo.blog.entities.Post;
import com.demo.blog.payloads.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostService {

    PostDto createPost (PostDto postDto, Integer userid, Integer categoryId) ;

    PostDto getPost(Integer Id);

    void deletePost (Integer postid);

    PostDto updatePost (PostDto postDto, Integer Id);

    List<PostDto> getAllPost(Integer pageNumber, Integer pageSize);

    List<PostDto> getPostsByCategory (Integer categoryId);

    List<PostDto> getPostsByUser (Integer Id);

}
