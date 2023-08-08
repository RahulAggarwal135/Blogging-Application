package com.demo.blog.services.impl;

import com.demo.blog.exceptions.ResourceNotFoundException;
import com.demo.blog.payloads.UserDto;
import com.demo.blog.repositories.UserRepo;
import com.demo.blog.services.UserService;
import com.demo.blog.entities.User;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userdto) {
        User user = this.modelMapper.map(userdto, User.class);
        User savedUser = this.userRepo.save(user);
        return this.modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userdto, Integer id) {

        User user = this.userRepo.findById(id).get();
        user.setPassword(userdto.getPassword());
        user.setName(userdto.getName());
        user.setAbout(userdto.getAbout());
        user.setEmail(userdto.getEmail());
        User updateduser = this.userRepo.save(user);
        return this.modelMapper.map(updateduser,UserDto.class);
    }

    @Override
    public UserDto getUserById(Integer userid) {
        try {
            User user = this.userRepo.findById(userid).get();
            return this.modelMapper.map(user, UserDto.class);
        }
        catch (Exception e) {
            throw new ResourceNotFoundException("User", "Id", userid);
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos = new ArrayList<UserDto>();
        for (User temp : users) {
            UserDto userDto = this.modelMapper.map(temp, UserDto.class);
            userDtos.add(userDto);
        }

        return userDtos;
    }

    @Override
    public void delete(Integer userId) {
        try{
            this.userRepo.deleteById(userId);
        }
        catch(Exception e) {
            throw new ResourceNotFoundException("user", "ID", userId);
        }
   }

}
