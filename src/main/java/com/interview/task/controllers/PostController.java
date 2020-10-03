package com.interview.task.controllers;

import com.interview.task.services.PostService;
import com.interview.task.services.RefreshDataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    private final RefreshDataService refreshDataService;

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
