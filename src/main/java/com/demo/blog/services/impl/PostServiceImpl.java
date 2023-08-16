package com.demo.blog.services.impl;

import com.demo.blog.entities.Category;
import com.demo.blog.entities.Post;
import com.demo.blog.entities.User;
import com.demo.blog.exceptions.ResourceNotFoundException;
import com.demo.blog.payloads.CategoryDto;
import com.demo.blog.payloads.PostDto;
import com.demo.blog.payloads.UserDto;
import com.demo.blog.repositories.CategoryRepo;
import com.demo.blog.repositories.PostRepo;
import com.demo.blog.repositories.UserRepo;
import com.demo.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    final static Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public PostDto createPost(PostDto postDto, Integer userid, Integer categoryId) {


        User user = this.userRepo.findById(userid).get();
        Category category = this.categoryRepo.findById(categoryId).get();
        Post post = this.modelMapper.map(postDto, Post.class);

        post.setImage("default.png");
        post.setUser(user);
        post.setCategory(category);
        post.setDate(new Date());
        Post created = this.postRepo.save(post);
        PostDto postDto1 =  this.modelMapper.map(created, PostDto.class);
        postDto1.setCategory(this.modelMapper.map(created.getCategory(), CategoryDto.class));
        postDto1.setUser(this.modelMapper.map(created.getUser(), UserDto.class));

       return postDto1;

    }

    @Override
    public PostDto getPost(Integer Id) {
        try {
            Post post = this.postRepo.findById(Id).get();
            return this.modelMapper.map(post, PostDto.class);
        }
        catch (Exception e) {
            throw new ResourceNotFoundException("Post", "Id", Id);
        }
    }
    @Override
    public void deletePost(Integer postid) {

        try{
            this.postRepo.deleteById(postid);
        }
        catch(Exception e) {
            throw new ResourceNotFoundException("post", "ID", postid);
        }
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer Id) {
        try {
            Post post = this.postRepo.findById(Id).get();
            post.setContent(postDto.getContent());
            post.setTitle(postDto.getTitle());
            Post updatedpost = this.postRepo.save(post);
            return this.modelMapper.map(updatedpost, PostDto.class);
        }
        catch (Exception e) {
            throw new ResourceNotFoundException("Category", "Id", Id);
        }
    }

    @Override
    public List<PostDto> getAllPost(Integer PageNumber, Integer PageSize, String sort) {

        Pageable pageable =  PageRequest.of(PageNumber, PageSize, Sort.by(sort));
        Page<Post> page = this.postRepo.findAll(pageable);
        List<Post> postList = page.getContent();
        List<PostDto> postDtoList = new ArrayList<PostDto>();
        for (Post temp : postList) {
            PostDto postDto  = this.modelMapper.map(temp, PostDto.class);
            postDtoList.add(postDto);
        }
        return postDtoList;
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).get();
        List<Post> postList =this.postRepo.getByCategory(cat);

        List<PostDto> postDtoList = new ArrayList<PostDto>();
        for (Post temp : postList) {
            PostDto postDto  = this.modelMapper.map(temp, PostDto.class);
            postDtoList.add(postDto);
        }
        return postDtoList;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer Id) {
        User user = this.userRepo.findById(Id).get();
        List<Post> postList =this.postRepo.getByUser(user);
        List<PostDto> postDtoList = new ArrayList<PostDto>();
        for (Post temp : postList) {
            PostDto postDto  = this.modelMapper.map(temp, PostDto.class);
            postDtoList.add(postDto);
        }
        return postDtoList;
    }
}
