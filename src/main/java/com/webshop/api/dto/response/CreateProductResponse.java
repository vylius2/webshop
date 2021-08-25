package com.webshop.api.dto.response;

import com.webshop.persistance.entity.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductResponse {

    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String picturePath;

    public CreateProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.picturePath = product.getPicturePath();
    }
}
