package com.interview.task.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@Data
public class Post {
    private int userId;
    @Id
    private int id;

    private String title;

    private String body;

    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    public Post() {
    }

    public Post(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
        this.postStatus = PostStatus.NORMAL;
    }
}
