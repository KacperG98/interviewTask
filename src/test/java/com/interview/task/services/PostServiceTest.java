package com.interview.task.services;

import com.interview.task.models.Post;
import com.interview.task.repositories.PostRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;
    
    @InjectMocks
    private PostService postService;

    @Test
    void getPostsTest() {
        //given
        when(postRepository.finaAllVisible()).thenReturn(prepareMockData());
        //when
        List<Post> posts = postService.getPosts();
        //then
        Assert.assertThat(posts, Matchers.hasSize(3));
    }

    @Test
    void savePostTest() {
        //given
        Post postExample = new Post(2,3, "Lorem", "Ipsum");
        when(postRepository.save(any(Post.class))).thenReturn(postExample);
        //when
        Post post = postService.savePost(postExample);
        //then
        Assert.assertEquals(post, postExample);
    }

    @Test
    void editPostTest() {
        Post postExample = new Post(2,3, "Timmy adventure", "travel is awesome");
        when(postRepository.findById(any())).thenReturn(Optional.of(postExample));
        Post post = postService.editPost(postExample);

        Assert.assertNotEquals(post, postExample);
    }

    @Test
    void editPostShouldFailWhenPostNotExist() {
        Post postExample = new Post(2,3, "Timmy adventure", "travel is awesome");
        when(postRepository.findById(any())).thenReturn(Optional.empty());
        Exception exception = assertThrows(RuntimeException.class, () -> {
            postService.editPost(postExample);
        });
        Assert.assertEquals(exception.getMessage(), "Can't edit non existing post" );
    }


    @Test
    void deletePostTest() {
        Post postExample = new Post(2,3, "Timmy adventure", "travel is awesome");
        when(postRepository.findById(any())).thenReturn(Optional.of(postExample));
        postService.deletePost(3);
    }

    @Test
    void deletePostShouldNotFailWhenPostNotExist() {
        when(postRepository.findById(any())).thenReturn(Optional.empty());
        postService.deletePost(3);

    }


    private List<Post> prepareMockData(){
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(1,1,"Test1","Lorem Ipsum"));
        posts.add(new Post(5,2,"Hello","Test message"));
        posts.add(new Post(1,3,"Test","test like mBank"));
        return posts;
    }
}