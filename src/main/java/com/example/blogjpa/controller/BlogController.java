package com.example.blogjpa.controller;

import com.example.blogjpa.domain.Article;
import com.example.blogjpa.dto.AddArticleRequest;
import com.example.blogjpa.dto.ArticleResponse;
import com.example.blogjpa.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController        // HTTP Response Body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
public class BlogController {
    private BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // HTTP요청이 'POST /api/articles' 경로일 때 해당 메소드로 매핑
    @PostMapping("/api/articles")
    public ResponseEntity<ArticleResponse> addArticle(@RequestBody AddArticleRequest request) { // RequestBody로 요청 본문 값 매핑
        Article article = blogService.save(request);
        ArticleResponse savedResponse = article.toResponse();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedResponse);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> list = blogService.findAll()
                .stream().map(ArticleResponse::new)
                .toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(list);
    }

    @GetMapping("/api/articles/{id}")
    public  ResponseEntity<ArticleResponse> findOneArticle(@PathVariable Long id) {
        Article article = blogService.findById(id);
        return ResponseEntity.ok(article.toResponse());
    }

    @DeleteMapping("api/articles/{id}")
    public ResponseEntity<Void> deleteOneArticle(@PathVariable Long id){
        blogService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("api/articles/{id}")
    public ResponseEntity<Article> updateOneArticle(@PathVariable Long id,
                                                    @RequestBody AddArticleRequest request){
        Article updated= blogService.updateOne(id, request);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("api/articles/title/{id}")
    public ResponseEntity<String> updateTitleArticle(@PathVariable Long id,
                                                    @RequestBody AddArticleRequest request){
        AddArticleRequest updated= blogService.updateTitle(id, request);
        String responseMessage = "조회해서 확인해보세요! 당신이 요청한 title 내용은 다음과 같습니다.\n title: " + updated.getTitle();
        return ResponseEntity.ok(responseMessage);
    }
}