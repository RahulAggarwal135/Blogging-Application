package com.demo.blog.controllers;

import com.demo.blog.payloads.CategoryDto;
import com.demo.blog.payloads.PostDto;
import com.demo.blog.services.PostService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostController {

    final static Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost (
            @RequestBody PostDto postDto,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId) {
        PostDto created = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto> (created,HttpStatus.CREATED);
    }

    @GetMapping("/posts")

    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam (value="pageNumber", defaultValue = "1", required = false) Integer pageNumber,
            @RequestParam (value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam (value = "sortBy", defaultValue = "PostId", required = false) String sort) {
        return ResponseEntity.ok(this.postService.getAllPost(pageNumber, pageSize, sort));
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPost(@PathVariable Integer postId) {
        return ResponseEntity.ok(this.postService.getPost(postId));
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost (@PathVariable ("postId") Integer Id) {
        this.postService.deletePost(Id);
        return new ResponseEntity(Map.of("message", "Post deleted"), HttpStatus.OK);
    }


    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost (@Valid @RequestBody PostDto postDto, @PathVariable ("postId") Integer Id) {
        PostDto postDto1 = this.postService.updatePost(postDto,Id);
        return new ResponseEntity<PostDto>(postDto1, HttpStatus.OK);

    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsBycategory(@PathVariable Integer categoryId) {
        List<PostDto> result = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>> (result,HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
        List<PostDto> result = this.postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDto>> (result,HttpStatus.OK);

    }

}
