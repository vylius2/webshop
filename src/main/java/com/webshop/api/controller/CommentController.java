package com.webshop.api.controller;

import com.webshop.exception.ProductNotFound;
import com.webshop.persistance.entity.Comment;
import com.webshop.service.CommentService;
import com.webshop.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    private ProductService productService;
    private CommentService commentService;

    public CommentController(CommentService commentService, ProductService productService){
        this.commentService = commentService;
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<Comment> getAllComments(){
        return commentService.getComments();
    }

    @GetMapping("product/{id}")
    public List<Comment> getAllCommentByProduct(@PathVariable("id") Long id){
        if (!productService.existsById(id)){
            System.out.println("Bys");
            throw new ProductNotFound(id);
        }
        return commentService.getAllCommentByProduct(id);
    }

}
