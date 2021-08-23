package com.webshop.api.dto.response;

import com.webshop.persistance.entity.Comment;
import com.webshop.persistance.entity.Product;
import com.webshop.persistance.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentResponse {

    private Long id;

    private String content;

    private Product product;

    private User user;

    private LocalDateTime created;

    public CreateCommentResponse(Comment comment){
        this.id = comment.getId();
        this.content = comment.getContent();
        this.product = comment.getProduct();
        this.user = comment.getUser();
        this.created = comment.getCreated();
    }
}
