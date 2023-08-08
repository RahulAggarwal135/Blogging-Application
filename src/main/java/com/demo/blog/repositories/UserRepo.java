package com.demo.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.blog.entities.User;
public interface UserRepo extends JpaRepository<User, Integer> {
}
