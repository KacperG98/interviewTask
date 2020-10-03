package com.interview.task.controllers;

import com.interview.task.models.Post;
import com.interview.task.models.PostStatus;
import lombok.Value;

@Value
public class PostDto {
    int userId;
    int id;
    String title;
    String body;

    public PostDto(Post post) {
        this.userId = post.getUserId();
        this.id = post.getId();
        this.title = post.getTitle();
        this.body = post.getBody();
    }

    public Post toPost(){
        Post post = new Post();
        post.setUserId(this.userId);
        post.setId(this.id);
        post.setTitle(this.title);
        post.setBody(this.body);
        post.setPostStatus(PostStatus.NORMAL);
        return post;
    }
}
