package com.interview.task.services;

import com.interview.task.models.Post;
import com.interview.task.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.desktop.OpenFilesEvent;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class PostService {
    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    public Post savePost(Post post){
        return postRepository.save(post);
    }

    public Post editPost(Post post){
        Optional<Post> op = postRepository.findById(post.getId());
        if (op.isEmpty()){
            throw new IllegalArgumentException("Can't edit non existing post");
        }
        Post p = op.get();
        p.setTitle(post.getTitle());
        p.setBody(post.getBody());
        return postRepository.save(p);
    }

    public void deletePost(int id){
        postRepository.deleteById(id);
    }
}
