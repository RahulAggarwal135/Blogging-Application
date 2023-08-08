package com.demo.blog.controllers;

import com.demo.blog.payloads.UserDto;
import com.demo.blog.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser (@Valid @RequestBody UserDto userDto) {
        UserDto createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<UserDto>(createUserDto, HttpStatus.CREATED);

    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser (@Valid @RequestBody UserDto userDto, @PathVariable ("userId") Integer userid) {
        UserDto updatedUser = this.userService.updateUser(userDto,userid);
        return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser (@PathVariable ("userId") Integer userid) {
        this.userService.delete(userid);
        return new ResponseEntity(Map.of("message", "User deleted"), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }


}
