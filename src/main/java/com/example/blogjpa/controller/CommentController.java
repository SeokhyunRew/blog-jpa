package com.example.blogjpa.controller;

import com.example.blogjpa.domain.Comment;
import com.example.blogjpa.dto.AddCommentRequest;
import com.example.blogjpa.dto.CommentResponse;
import com.example.blogjpa.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments/{articleId}")
    public ResponseEntity<List<CommentResponse>> findAllComment(@PathVariable Long articleId) {
        List<CommentResponse> list = commentService.findAll(articleId)
                .stream().map(CommentResponse::new)
                .toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(list);
    }

    @PostMapping("/comments/{articleId}")
    public  ResponseEntity<CommentResponse>  addComment(@PathVariable Long articleId, @RequestBody AddCommentRequest request) {
       Comment comment = commentService.save(articleId, request);
       CommentResponse savedResponse = comment.toResponse();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedResponse);
    }
    @GetMapping("/comments/{articleId}/{commentId}")
    public ResponseEntity<List<CommentResponse>> findCommentbyID(@PathVariable Long articleId, @PathVariable Long commentId) {
        List<CommentResponse> list = commentService.findCommentID(articleId, commentId)
                .stream().map(CommentResponse::new)
                .toList();
        return ResponseEntity.status(HttpStatus.OK)
                .body(list);
    }

}
