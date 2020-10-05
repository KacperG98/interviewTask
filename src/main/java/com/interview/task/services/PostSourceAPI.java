package com.interview.task.services;

import com.interview.task.controllers.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.util.List;

@Service
public class PostSourceAPI {
    @Value("another.uri")
    private String uri;
    @Autowired
    private RestTemplate restTemplate;

    public PostSourceAPI() {
    }

    public PostSourceAPI(String uri, RestTemplate restTemplate) {
        this.uri = uri;
        this.restTemplate = restTemplate;
    }

    public List<PostDto> getData(){
        ResponseEntity<List<PostDto>> res = restTemplate
                .exchange(URLEncoder.encode(uri), HttpMethod.GET,
                HttpEntity.EMPTY, new ParameterizedTypeReference<List<PostDto>>() {});

        return res.getBody();
    }
}
