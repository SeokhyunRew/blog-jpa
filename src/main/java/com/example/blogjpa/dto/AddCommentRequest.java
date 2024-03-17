package com.example.blogjpa.dto;

import com.example.blogjpa.domain.Article;
import com.example.blogjpa.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddCommentRequest {
    private Long article_id;
    private String body;

    public Comment toEntity(Long article_id) {	// 생성자를 사용해 객체 생성
        return Comment.builder()
                .body(body)
                .article_id(article_id)
                .build();
    }
}
