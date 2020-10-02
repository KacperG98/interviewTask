package com.interview.task.conntrollers;

import com.interview.task.DTO.PostDto;
import com.interview.task.models.Post;
import com.interview.task.services.PostService;
import com.interview.task.services.PostSourceAPI;
import com.interview.task.services.RefreshDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    private PostSourceAPI postSourceAPI;
    
    private RefreshDataService refreshDataService;

    @Autowired
    public PostController(PostService postService, PostSourceAPI postSourceAPI, RefreshDataService refreshDataService) {
        this.postService = postService;
        this.postSourceAPI = postSourceAPI;
        this.refreshDataService = refreshDataService;
    }

    @GetMapping()
    public List<PostDto> getPosts(){
        Stream<PostDto> posts = postService.getPosts().stream().map(p -> new PostDto(p));
        return posts.collect(Collectors.toList());

    }

    @PostMapping("/reload")
    public String refresh(){
        refreshDataService.refresh();
        return "Done";
    }

    @PutMapping()
    public PostDto editPost(@RequestBody PostDto post){
        return new PostDto(postService.editPost(post.toPost()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        postService.deletePost(id);
    }

}
