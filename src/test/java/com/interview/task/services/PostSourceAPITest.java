package com.interview.task.services;

import com.interview.task.controllers.PostDto;
import com.interview.task.models.Post;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class PostSourceAPITest {

    PostSourceAPI postSourceAPI;

    RestTemplate restTemplate = mock(RestTemplate.class);

    @BeforeEach
    public void init(){
        postSourceAPI =  new PostSourceAPI("test", restTemplate);
    }

    @Test
    public void getDataTest() {
        //given
        List<PostDto> posts = List.of(
                new PostDto(new Post(2,2,"t","tdd")),
                new PostDto(new Post(2,3,"b","bdd")),
                new PostDto(new Post(2,4,"c","clean code"))
        );

        ResponseEntity<List<PostDto>> myEntity =
                new ResponseEntity<>(posts, HttpStatus.ACCEPTED);
        Mockito.when(restTemplate.exchange(
                anyString(),
                any(HttpMethod.class),
                Matchers.<HttpEntity<List<PostDto>>>any(),
                Matchers.<ParameterizedTypeReference<List<PostDto>>>any())
        ).thenReturn(myEntity);

        //when
        List<PostDto> result = postSourceAPI.getData();
        //then
        Assert.assertEquals(result, posts);
    }
}