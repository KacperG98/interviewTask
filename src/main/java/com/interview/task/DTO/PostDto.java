package com.interview.task.DTO;

import com.interview.task.models.Post;

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
