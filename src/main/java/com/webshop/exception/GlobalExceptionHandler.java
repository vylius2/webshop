package com.webshop.exception;

import com.webshop.api.dto.error.Error;
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
        ErrorResponse errorResponse = new ErrorResponse(404, Error.PRODUCT_NOT_FOUND, message);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
