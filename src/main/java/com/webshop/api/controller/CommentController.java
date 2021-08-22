package com.webshop.api.controller;

import com.webshop.api.dto.request.CreateCommentRequest;
import com.webshop.api.dto.response.CreateCommentResponse;
import com.webshop.exception.ProductNotFound;
import com.webshop.persistance.entity.Comment;
import com.webshop.service.CommentService;
import com.webshop.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    private final ProductService productService;
    private final CommentService commentService;

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

        //TODO IF VELIAU GALI BUTI NEBUTINAS!!!

        if (!productService.existsById(id)){
            throw new ProductNotFound(id);
        }
        return commentService.getAllCommentByProduct(id);
    }

    @PostMapping("/create")
    public CreateCommentResponse create(@Valid @RequestBody CreateCommentRequest createCommentRequest){
        return null;

        //TODO FINISH THIS!!

    }

    //DELETE

    //EDIT
}
