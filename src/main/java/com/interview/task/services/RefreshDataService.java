package com.interview.task.services;

import com.interview.task.DTO.PostDto;
import com.interview.task.models.Post;
import com.interview.task.models.PostStatus;
import com.interview.task.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RefreshDataService {

    private PostRepository postRepository;

    private PostSourceAPI postSourceAPI;

    @Autowired
    public RefreshDataService(PostRepository postRepository, PostSourceAPI postSourceAPI) {
        this.postRepository = postRepository;
        this.postSourceAPI = postSourceAPI;
    }

    @Scheduled(cron = "0 0 12 * * ?")
    public void refresh(){
        List<PostDto> remotePosts = postSourceAPI.getData();

        remotePosts.stream().map(postDto -> postDto.toPost() ).forEach(post -> {
            Optional<Post> op = postRepository.findById(post.getId());
            if (op.isEmpty()){
                postRepository.save(post);
            }
            else{
                if(post.getPostStatus() == PostStatus.NORMAL);
                {
                    postRepository.save(post);
                }
            }
        });

    }
}
