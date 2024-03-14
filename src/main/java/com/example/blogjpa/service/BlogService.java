package com.example.blogjpa.service;

import com.example.blogjpa.domain.Article;
import com.example.blogjpa.dto.AddArticleRequest;
import com.example.blogjpa.repository.BlogRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(Long id){
        //단건 조회시 조회하려는 id 없으면 오류메세지
        return blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found id" + id));
//        return blogRepository.findById(id).orElse(new Article());
    }

    public void deleteById(Long id){
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article updateOne(Long id, AddArticleRequest request) {
        //begin transaction
        Article article = findById(id);
        article.update(request.getTitle(), request.getContent());

        //commit / rollback
        return article;
    }

    @Transactional
    public AddArticleRequest updateTitle(Long id, AddArticleRequest request){
        Article article = findById(id);
        int updateTitleNum;
        updateTitleNum = blogRepository.updateTitle(id, request.getTitle());

        if(updateTitleNum > 0){
            return request;
        }
        return request;
    }
}