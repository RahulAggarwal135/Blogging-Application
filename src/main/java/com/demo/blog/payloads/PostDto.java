package com.demo.blog.payloads;

import com.demo.blog.entities.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
    private String title;
    private String content;
    private String image;

    private Date date;

    private CategoryDto category;
    private UserDto user;


}
