package com.webshop.api.dto.request;

import com.webshop.persistance.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UpdateCommentRequest {
    @NotBlank
    private String content;
    @NotNull
    private Long productId;
    @NotNull
    private Long userId;
}
