package com.webshop.api.controller;

import com.webshop.api.dto.request.CreateCommentRequest;
import com.webshop.api.dto.request.UpdateCommentRequest;
import com.webshop.api.dto.request.UpdateProductRequest;
import com.webshop.api.dto.response.CreateCommentResponse;
import com.webshop.api.dto.response.UpdateCommentResponse;
import com.webshop.api.dto.response.UpdateProductResponse;
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

    @PostMapping("/create/{productId}")
    public CreateCommentResponse create(@PathVariable Long productId,
                                        @Valid @RequestBody CreateCommentRequest createCommentRequest){
        return new CreateCommentResponse(commentService.save(createCommentRequest, productId));
    }

    @DeleteMapping("/delete/{commentId}")
    public void delete(@PathVariable Long commentId){
        commentService.delete(commentId);
    }

    @PutMapping("/update/{id}")
    public UpdateCommentResponse update(@PathVariable("id") Long id,
                                        @Valid @RequestBody UpdateCommentRequest updateCommentRequest){
        return new UpdateCommentResponse(commentService.update(id, updateCommentRequest));
    }
}
