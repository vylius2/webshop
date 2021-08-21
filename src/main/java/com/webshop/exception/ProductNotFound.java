package com.webshop.exception;

import lombok.Getter;

@Getter
public class ProductNotFound extends RuntimeException{
    private Long id;
    public ProductNotFound(Long id){
        this.id = id;
    }
}
