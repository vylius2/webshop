package com.webshop.api.controller;

import com.webshop.api.dto.request.CreateCommentRequest;
import com.webshop.api.dto.request.UpdateCommentRequest;
import com.webshop.api.dto.response.CreateCommentResponse;
import com.webshop.api.dto.response.UpdateCommentResponse;
import com.webshop.exception.ProductNotFound;
import com.webshop.persistance.entity.Comment;
import com.webshop.service.CommentService;
import com.webshop.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/comments")
public class CommentController {

    private final ProductService productService;
    private final CommentService commentService;

    public CommentController(CommentService commentService, ProductService productService){
        this.commentService = commentService;
        this.productService = productService;
    }

    @GetMapping
    public List<Comment> getAllComments(){
        return commentService.getComments();
    }

    @GetMapping("/{id}")
    public List<Comment> getAllCommentByProduct(@PathVariable("id") Long id){
        if (!productService.existsById(id)){
            throw new ProductNotFound(id);
        }
        return commentService.getAllCommentByProduct(id);
    }

    @PostMapping("/product/{productId}")
    public CreateCommentResponse create(@PathVariable Long productId,
                                        @Valid @RequestBody CreateCommentRequest createCommentRequest){
        return new CreateCommentResponse(commentService.save(createCommentRequest, productId));
    }

    @DeleteMapping("/{commentId}")
    public void delete(@PathVariable Long commentId){
        commentService.delete(commentId);
    }

    @PutMapping("/{id}")
    public UpdateCommentResponse update(@PathVariable("id") Long id,
                                        @Valid @RequestBody UpdateCommentRequest updateCommentRequest){
        return new UpdateCommentResponse(commentService.update(id, updateCommentRequest));
    }
}
