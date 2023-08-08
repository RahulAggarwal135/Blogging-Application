package com.demo.blog.services;

import com.demo.blog.payloads.CommentDto;

import java.util.List;

public interface CommentService {


    CommentDto createComment (CommentDto commentDto, Integer postId) ;

    CommentDto getComment(Integer Id);

    void deleteComment (Integer commentId);

    CommentDto updateComment (CommentDto commentDto, Integer Id);

    List<CommentDto> getCommentByPost(Integer postId);

}
