package com.webshop.api.controller;

import com.webshop.api.dto.UserDTO;
import com.webshop.api.dto.request.RegisterRequest;
import com.webshop.api.dto.response.LoginResponse;
import com.webshop.persistance.entity.User;
import com.webshop.service.JwtService;
import com.webshop.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping
public class MainController {
    private final JwtService jwtService;

    private final UserService userService;

    public MainController(JwtService jwtService, UserService userService){
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public LoginResponse login(@AuthenticationPrincipal User user) {
        return new LoginResponse(jwtService.createToken(user), new UserDTO(user));
    }
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO register(@Valid @RequestBody RegisterRequest registerRequest) {
        return new UserDTO(userService.createUser(
                new User(registerRequest)));
    }
}
