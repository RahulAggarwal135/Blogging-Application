package com.demo.blog.entities;

import com.demo.blog.payloads.CategoryDto;
import com.demo.blog.payloads.UserDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    private String title;
    private String content;
    private String image;
    private Date date;

    @ManyToOne
    private Category category;
    @ManyToOne
    private User user;

    @OneToMany (mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> commentList;

}
