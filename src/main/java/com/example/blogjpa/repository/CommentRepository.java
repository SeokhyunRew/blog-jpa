package com.example.blogjpa.repository;

import com.example.blogjpa.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where c.article_id = :article_id")
    List<Comment> findAllComment (Long article_id);

    @Query("select c from Comment c where c.id = :id and c.article_id = :article_id")
    List<Comment> findCommentbyID (Long article_id, Long id);

    @Modifying
    @Query("INSERT INTO Comment( article_id, body, createdAt) VALUES (:#{#comment.getArticle_id()}, :#{#comment.getBody()}, CURRENT_TIMESTAMP)")
    int saveComment (Comment comment);

}
