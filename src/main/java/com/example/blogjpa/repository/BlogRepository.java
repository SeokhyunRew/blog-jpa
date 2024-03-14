package com.example.blogjpa.repository;

import com.example.blogjpa.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Article, Long> {

    //List<Article> findByTitle(String title);
    @Modifying
    @Query("update Article set title= :title where id = :id")
    int updateTitle(Long id, String title);
}