package com.webshop.exception;

import lombok.Data;

@Data
public class CommentNotFound extends RuntimeException{
    private Long id;
    public CommentNotFound(Long id){
        this.id = id;
    }
}
