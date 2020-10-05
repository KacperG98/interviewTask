package com.interview.task.controllers;

import com.interview.task.models.Post;
import com.interview.task.services.PostService;
import com.interview.task.services.RefreshDataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PostControllerTest {
    @Mock
    PostService postService;

    @Mock
    RefreshDataService refreshDataService;

    @InjectMocks
    PostController postController;


    @Test
    public void getPosts() {
        when(postService.getPosts()).thenReturn(prepareMockData());
        List<PostDto> res = postController.getPosts();
    }

    @Test
    void refresh() {
        doNothing().when(refreshDataService).refresh();
        String msg = postController.refresh();
    }

    @Test
    void editPost() {
        Post postExample = new Post(2,3, "Timmy adventure", "travel is awesome");
        when(postService.editPost(any())).thenReturn(postExample);
        PostDto post = postController.editPost(new PostDto(postExample));
    }

    @Test
    void delete(){
        doNothing().when(postService).deletePost(anyInt());
        postController.delete(2);
    }

    private List<Post> prepareMockData(){
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(1,1,"Test1","Lorem Ipsum"));
        posts.add(new Post(5,2,"Hello","Test message"));
        posts.add(new Post(1,3,"Test","test like mBank"));
        return posts;
    }

}