package com.webshop.service;

import com.webshop.persistance.entity.User;
import com.webshop.persistance.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getById(Long id){
        return userRepository.getById(id);
    }
}
