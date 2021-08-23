package com.webshop.api.dto.response;

import com.webshop.persistance.entity.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateCommentResponse {
    private Long id;
    private String content;

    public UpdateCommentResponse(Comment comment){
        this.id = comment.getId();
        this.content = comment.getContent();
    }
}
