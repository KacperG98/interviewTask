package com.interview.task.services;

import com.interview.task.models.Post;
import com.interview.task.models.PostStatus;
import com.interview.task.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        p.setPostStatus(PostStatus.EDITED);
        return postRepository.save(p);
    }

    public void deletePost(int id){
        Optional<Post> oPost = postRepository.findById(id);
        Post post = oPost.get();
        post.setPostStatus(PostStatus.DELETED);
        postRepository.save(post);
    }
}
