package com.demo.blog.controllers;

import com.demo.blog.payloads.CommentDto;
import com.demo.blog.services.CommentService;
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
public class CommentController {

    final static Logger logger = LoggerFactory.getLogger(CommentController.class);
    @Autowired
    CommentService commentService;

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<CommentDto> createPost (
            @RequestBody CommentDto commentDto,
            @PathVariable Integer postId) {
        logger.info("hello");

        CommentDto created = this.commentService.createComment(commentDto, postId);
        logger.info("aaa");
        logger.info(created.toString());
        return new ResponseEntity<CommentDto> (created, HttpStatus.CREATED);
    }


    @GetMapping("/comment/{commentId}")
    public ResponseEntity<CommentDto> getComment(@PathVariable Integer commentId) {
        return ResponseEntity.ok(this.commentService.getComment(commentId));
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<?> deleteComment (@PathVariable ("commentId") Integer Id) {
        this.commentService.deleteComment(Id);
        return new ResponseEntity(Map.of("message", "Comment deleted"), HttpStatus.OK);
    }


    @PutMapping("/comment/{commentId}")
    public ResponseEntity<CommentDto> updateComment (@Valid @RequestBody CommentDto commentDto, @PathVariable ("commentId") Integer Id) {
        CommentDto updated = this.commentService.updateComment(commentDto,Id);
        return new ResponseEntity<CommentDto>(updated, HttpStatus.OK);
    }

    @GetMapping("/post/{postId}/comment")
    public ResponseEntity<List<CommentDto>> getCommentByPost(@PathVariable Integer postId) {
        List<CommentDto> result = this.commentService.getCommentByPost(postId);
        return new ResponseEntity<List<CommentDto>> (result,HttpStatus.OK);

    }

}
