package com.interview.task.services;

import com.interview.task.models.Post;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
class PostSourceAPITest {

    @Spy
    PostSourceAPI postSourceAPI;

    @Test
    void getDataTest() {
        List<Post> posts = List.of(
                new Post(2,2,"t","tdd"),
                new Post(2,3,"b","bdd"),
                new Post(2,4,"c","clean code")
        );
        given(postSourceAPI.getData())
                .willReturn(posts);
        List<Post> result = postSourceAPI.getData();
        Assert.assertEquals(result, posts);
    }
}