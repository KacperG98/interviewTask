package com.interview.task.repositories;

import com.interview.task.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("select p from Post p where p.postStatus <> com.interview.task.models.PostStatus.DELETED")
    List<Post> finaAllVisible();
}
