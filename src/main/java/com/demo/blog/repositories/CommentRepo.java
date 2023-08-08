package com.demo.blog.repositories;

import com.demo.blog.entities.Comment;
import com.demo.blog.entities.Post;
import com.demo.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

    List<Comment> getByPost(Post post);

}
