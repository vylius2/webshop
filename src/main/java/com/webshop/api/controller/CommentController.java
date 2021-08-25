package com.webshop.api.controller;

import com.webshop.api.dto.request.CreateCommentRequest;
import com.webshop.api.dto.request.UpdateCommentRequest;
import com.webshop.api.dto.response.CreateCommentResponse;
import com.webshop.api.dto.response.UpdateCommentResponse;
import com.webshop.exception.ProductNotFound;
import com.webshop.persistance.entity.Comment;
import com.webshop.service.CommentService;
import com.webshop.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @ApiOperation(value = "Get all comments", tags = "getAll", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully get list of comments"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Comment> getAll(){
        return commentService.getComments();
    }

    @ApiOperation(value = "Get all comments", tags = "getAllCommentsByProduct", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully get list of comments"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Product does not exist")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public List<Comment> getAllCommentsByProduct(@PathVariable("id") Long id){
        if (!productService.existsById(id)){
            throw new ProductNotFound(id);
        }
        return commentService.getAllCommentByProduct(id);
    }

    @ApiOperation(value = "Create comment", tags = "create", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created comment record"),
            @ApiResponse(code = 400, message = "Validation failed"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Product does not exist")
    })
    @PostMapping("/product/{productId}")
    @PreAuthorize("hasRole('USER')")
    public CreateCommentResponse create(@PathVariable Long productId,
                                        @Valid @RequestBody CreateCommentRequest createCommentRequest){
        return new CreateCommentResponse(commentService.save(createCommentRequest, productId));
    }

    @ApiOperation(value = "Delete comment by id", tags = "delete", httpMethod = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully delete comment by id"),
            @ApiResponse(code = 404, message = "Comment not found error"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long commentId){
        commentService.delete(commentId);
    }

    @ApiOperation(value = "Update comment", tags = "update", httpMethod = "PUT")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated comment record"),
            @ApiResponse(code = 400, message = "Validation failed"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public UpdateCommentResponse update(@PathVariable("id") Long id,
                                        @Valid @RequestBody UpdateCommentRequest updateCommentRequest){
        return new UpdateCommentResponse(commentService.update(id, updateCommentRequest));
    }
}
