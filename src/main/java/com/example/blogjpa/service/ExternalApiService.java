package com.example.blogjpa.service;

import com.example.blogjpa.dto.AddArticleRequest;
import org.springframework.stereotype.Service;

@Service
public class ExternalApiService {

    private final BlogService blogService;

    public ExternalApiService(BlogService blogService) {
        this.blogService = blogService;
    }

    public void saveArticle(String title, String content) {
        blogService.save(new AddArticleRequest(title, content));
    }
}