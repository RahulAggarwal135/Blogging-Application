package com.demo.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
    private int postId;
    private String title;
    private String content;
    private String image;

    private Date date;

    private CategoryDto category;
    private UserDto user;


}
