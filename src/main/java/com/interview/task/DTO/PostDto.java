package com.interview.task.DTO;

import com.interview.task.models.Post;
import com.interview.task.models.PostStatus;

public class PostDto {
    private int userId;
    private int id;
    private String title;
    private String body;

    public PostDto(Post post) {
        this.id = post.getId();
        this.userId = post.getUserId();
        this.title = post.getTitle();
        this.body = post.getBody();
    }

    public Post toPost(){
        Post post = new Post();
        post.setUserId(this.userId);
        post.setPostStatus(PostStatus.NORMAL);
        post.setId(this.id);
        post.setBody(this.body);
        post.setTitle(this.title);
        return post;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
