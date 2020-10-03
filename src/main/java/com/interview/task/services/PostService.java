package com.interview.task.services;

import com.interview.task.models.Post;
import com.interview.task.models.PostStatus;
import com.interview.task.repositories.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<Post> getPosts(){
        return postRepository.finaAllVisible();
    }

    public Post savePost(Post post){
        return postRepository.save(post);
    }

    public Post editPost(Post post){
        Optional<Post> oPost = postRepository.findById(post.getId());
        if (oPost.isEmpty()){
            throw new IllegalArgumentException("Can't edit non existing post");
        }
        Post p = oPost.get();
        p.setTitle(post.getTitle());
        p.setBody(post.getBody());
        p.setPostStatus(PostStatus.EDITED);
        return postRepository.save(p);
    }

    public void deletePost(int id){
        Optional<Post> oPost = postRepository.findById(id);
        oPost.ifPresent(p -> {
            p.setPostStatus(PostStatus.DELETED);
            postRepository.save(p);
        });
    }
}
