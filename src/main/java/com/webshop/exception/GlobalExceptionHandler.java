package com.webshop.exception;

import com.webshop.api.dto.enumerated.Error;
import com.webshop.api.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<ErrorResponse> handle(ProductNotFound e){
        String message = String.format("Product with id: %d was not found", e.getId());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), Error.PRODUCT_NOT_FOUND, message);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CommentNotFound.class)
    public ResponseEntity<ErrorResponse> handle(CommentNotFound e){
        String message = String.format("Comment with id: %d was not found", e.getId());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), Error.COMMENT_NOT_FOUND, message);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<ErrorResponse> handle(UserAlreadyExists e){
        String message = String.format("User with username: %s already exists", e.getUsername());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), Error.USER_ALREADY_EXISTS, message);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
