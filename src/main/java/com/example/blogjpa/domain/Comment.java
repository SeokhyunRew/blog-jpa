package com.example.blogjpa.domain;

import com.example.blogjpa.dto.CommentResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "article_id", nullable = false)
    private Long article_id;

    @Column(name = "body", nullable = false)
    private String body;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public Comment(Long article_id, String body) {
        this.article_id = article_id;
        this.body = body;
    }

    public CommentResponse toResponse() {
        return CommentResponse.builder()
                .id(id)
                .body(body)
                .created_at(createdAt)
                .build();
    }
    public void update(String body) {
        this.body = body;
    }
}