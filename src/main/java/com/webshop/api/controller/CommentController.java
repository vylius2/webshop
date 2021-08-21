package com.webshop.api.controller;

import com.webshop.persistance.entity.Comment;
import com.webshop.service.CommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping("/all")
    public List<Comment> getAllComments(){
        System.out.println("AAAAAAAAAAAAAAAAAA");
        return commentService.getComments();
    }
}
