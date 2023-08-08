package com.demo.blog.services;

import com.demo.blog.controllers.CommentController;
import com.demo.blog.entities.Category;
import com.demo.blog.entities.Comment;
import com.demo.blog.entities.Post;
import com.demo.blog.entities.User;
import com.demo.blog.exceptions.ResourceNotFoundException;
import com.demo.blog.payloads.CategoryDto;
import com.demo.blog.payloads.CommentDto;
import com.demo.blog.payloads.PostDto;
import com.demo.blog.payloads.UserDto;
import com.demo.blog.repositories.CommentRepo;
import com.demo.blog.repositories.PostRepo;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    final static Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    PostRepo postRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CommentRepo commentRepo;






    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {

        Post post = this.postRepo.findById(postId).get();
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment created = this.commentRepo.save(comment);
        return this.modelMapper.map(created, CommentDto.class);
    }

    @Override
    public CommentDto getComment(Integer Id) {
         try{

            Comment comment = this.commentRepo.getById(Id);
            return this.modelMapper.map(comment, CommentDto.class);
            }

            catch (Exception e) {
            throw new ResourceNotFoundException("Comment", "Id", Id);
        }
    }



    @Override
    public void deleteComment(Integer commentId) {

        try{
            this.commentRepo.deleteById(commentId);
        }
        catch(Exception e) {
            throw new ResourceNotFoundException("comment", "ID", commentId);
        }

    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Integer Id) {
        try {
            Comment comment = this.commentRepo.findById(Id).get();
            comment.setContent(commentDto.getContent());
            Comment updatedcomment = this.commentRepo.save(comment);
            return this.modelMapper.map(updatedcomment, CommentDto.class);
        }
        catch (Exception e) {
            throw new ResourceNotFoundException("Comment", "Id", Id);
        }
    }

    @Override
    public List<CommentDto> getCommentByPost(Integer postId) {
        try {
        Post post = this.postRepo.findById(postId).get();
        List<Comment> commentList =this.commentRepo.getByPost(post);
        List<CommentDto> commentDtoList = new ArrayList<CommentDto>();
        for (Comment comment : commentList) {
            CommentDto commentDto = this.modelMapper.map(comment,CommentDto.class);
            commentDtoList.add(commentDto);
        }
        return commentDtoList;
        }
        catch (Exception e) {
            throw new ResourceNotFoundException("Comment", "Id", postId);
        }
    }

}
