package com.webshop.persistance.entity;

import com.webshop.api.dto.request.CreateCommentRequest;
import com.webshop.api.dto.request.UpdateCommentRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@Table(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    private LocalDateTime created;

    public Comment(CreateCommentRequest createCommentRequest, Product product){
        this.product = product;
        this.content = createCommentRequest.getContent();
        this.user = new User();
        this.user.setId(3L);
    }

    public Comment(Long id, UpdateCommentRequest updateCommentRequest) {
        this.id = id;
        this.content = updateCommentRequest.getContent();
    }
    public Comment(Long id, UpdateCommentRequest updateCommentRequest, User user, Product product) {
        this.id = id;
        this.content = updateCommentRequest.getContent();
        this.user = user;
        this.product = product;
        this.created = LocalDateTime.now();
    }
}
