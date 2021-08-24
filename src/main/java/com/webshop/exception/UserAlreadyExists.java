package com.webshop.exception;

import lombok.Data;

@Data
public class UserAlreadyExists extends RuntimeException{
    private final String username;

    public UserAlreadyExists(String username){
        this.username = username;
    }
}
