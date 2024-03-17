package com.example.blogjpa.service;

import com.example.blogjpa.domain.Comment;
import com.example.blogjpa.dto.AddCommentRequest;
import com.example.blogjpa.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> findAll(Long article_id){
        return commentRepository.findAllComment(article_id);
    }

    public List<Comment> findCommentID(Long article_id, Long id){
        return commentRepository.findCommentbyID(article_id, id);
    }

    @Transactional
    public Comment save(Long article_id, AddCommentRequest request){
        Comment comment = request.toEntity(article_id);
        if(commentRepository.saveComment(comment)>0){
            return comment;
        }else{
            return comment;
        }
    }
}
