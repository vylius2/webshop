package com.webshop.api.dto.response;

import com.webshop.persistance.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String picturePath;

    public UpdateProductResponse(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.picturePath = product.getPicturePath();
    }
}
