package com.interview.task.services;

import com.interview.task.DTO.PostDto;
import com.interview.task.models.Post;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Matchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        ResponseEntity res = mock(ResponseEntity.class);


        when(restTemplate.exchange( anyString(),
                any(HttpMethod.class),
                Matchers.<HttpEntity<?>> any(),
                Matchers.<Class<String>> any())).thenReturn(res);


        //given(restTemplate.exchange(anyString(), eq(HttpMethod.GET), eq(HttpEntity.EMPTY), any(Class.class))).willReturn(res);
        given(res.getBody()).willReturn(posts);
        //when
        List<PostDto> result = postSourceAPI.getData();
        //then
        Assert.assertEquals(result, posts);
    }
}