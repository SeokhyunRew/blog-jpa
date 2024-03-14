package com.example.blogjpa.dto;

import com.example.blogjpa.domain.Article;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleResponse {
    private Long id;
    private String title;
    private String content;

    public ArticleResponse(Article article) {
        id = article.getId();
        title = article.getTitle();
        content = article.getContent();
    }
}
