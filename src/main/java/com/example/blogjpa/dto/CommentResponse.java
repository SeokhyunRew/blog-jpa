package com.example.blogjpa.dto;

import com.example.blogjpa.domain.Comment;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponse{
    private Long id;
    private String body;
    private LocalDateTime created_at;

    public CommentResponse(Comment comment) {
        id = comment.getId();
        body = comment.getBody();
        created_at = comment.getCreatedAt();
    }
}
