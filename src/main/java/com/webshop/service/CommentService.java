package com.webshop.service;

import com.webshop.persistance.entity.Comment;
import com.webshop.persistance.repository.CommentRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@NoArgsConstructor
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

//    public CommentService (CommentRepository commentRepository){
//        this.commentRepository = commentRepository;
//    }

    public List<Comment> getComments(){
        return commentRepository.findAll();
    }


    public List<Comment> getAllCommentByProduct(Long id) {
        return commentRepository.findAllByProductId(id);
    }
}
