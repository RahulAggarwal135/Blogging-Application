package com.demo.blog.repositories;

import com.demo.blog.entities.Category;
import com.demo.blog.entities.Post;
import com.demo.blog.entities.User;
import com.demo.blog.payloads.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo  extends JpaRepository<Post, Integer> {
    List<Post> getByUser(User User);

    List<Post> getByCategory (Category category);
}
