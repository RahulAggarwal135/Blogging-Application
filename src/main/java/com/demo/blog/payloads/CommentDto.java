package com.demo.blog.payloads;

import com.demo.blog.entities.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CommentDto {

    private int commentId;
    private String content;
    private PostDto post;
}
