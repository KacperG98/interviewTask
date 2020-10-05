package com.interview.task.services;

import com.interview.task.controllers.PostDto;
import com.interview.task.models.Post;
import com.interview.task.models.PostStatus;
import com.interview.task.repositories.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RefreshDataServiceTest {
    @Mock
    PostRepository postRepository;

    @Mock
    PostSourceAPI postSourceAPI;

    @InjectMocks
    RefreshDataService refreshDataService;

    @Test
    void refreshWithNoExistId() {
        Post p = new Post(2,2,"test","test");
        List<PostDto> pDto = List.of(new PostDto(p));
        when(postSourceAPI.getData()).thenReturn(pDto);
        when(postRepository.findById(anyInt())).thenReturn(Optional.empty());
        when(postRepository.save(any(Post.class))).thenReturn(p);

        refreshDataService.refresh();
    }

    @Test
    void refreshWithDeletedStatus() {
        Post p = new Post(2,2,"test","test");
        p.setPostStatus(PostStatus.DELETED);
        List<PostDto> pDto = List.of(new PostDto(p));
        when(postSourceAPI.getData()).thenReturn(pDto);
        when(postRepository.findById(anyInt())).thenReturn(Optional.of(p));

        refreshDataService.refresh();
    }

    @Test
    void refreshExistingPost() {
        Post p = new Post(2,2,"test","test");
        p.setPostStatus(PostStatus.NORMAL);
        List<PostDto> pDto = List.of(new PostDto(p));
        when(postSourceAPI.getData()).thenReturn(pDto);
        when(postRepository.findById(anyInt())).thenReturn(Optional.of(p));
        when(postRepository.save(any(Post.class))).thenReturn(p);

        refreshDataService.refresh();
    }
}