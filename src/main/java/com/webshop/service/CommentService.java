package com.webshop.service;

import com.webshop.api.dto.request.CreateCommentRequest;
import com.webshop.api.dto.request.UpdateCommentRequest;
import com.webshop.exception.CommentNotFound;
import com.webshop.persistance.entity.Comment;
import com.webshop.persistance.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {


    private final CommentRepository commentRepository;
    private final ProductService productService;
    private final UserService userService;


    public CommentService (CommentRepository commentRepository, ProductService productService, UserService userService){
        this.commentRepository = commentRepository;
        this.productService = productService;
        this.userService = userService;
    }

    public List<Comment> getComments(){
        return commentRepository.findAll();
    }


    public List<Comment> getAllCommentByProduct(Long id) {
        return commentRepository.findAllByProductId(id);
    }

    public Comment save(CreateCommentRequest createCommentRequest, Long productId){
        return commentRepository.save(new Comment(createCommentRequest, productService.getById(productId)));
    }

    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }
    public Comment update(Long id, UpdateCommentRequest updateCommentRequest){
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFound(id));
        comment.setContent(updateCommentRequest.getContent());
        return commentRepository.save(comment);

    }
}
