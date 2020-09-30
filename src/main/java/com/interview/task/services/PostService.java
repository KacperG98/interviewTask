package com.interview.task.services;

import com.interview.task.models.Post;
import com.interview.task.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Iterable<Post> getPosts(){
        return null;
    }

    public Post savePost(Post post){
        return null;
    }

    public Post editPost(Post post){
        return null;
    }

    public void deletePost(int id){

    }
}
