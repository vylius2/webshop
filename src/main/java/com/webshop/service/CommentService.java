package com.webshop.service;

import com.webshop.persistance.entity.Comment;
import com.webshop.persistance.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {


    private CommentRepository commentRepository;

    @Autowired
    public CommentService (CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    public List<Comment> getComments(){
        return commentRepository.findAll();
    }


    public List<Comment> getAllCommentByProduct(Long id) {
        return commentRepository.findAllByProductId(id);
    }
}
