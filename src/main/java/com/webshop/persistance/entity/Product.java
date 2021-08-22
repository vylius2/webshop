package com.webshop.persistance.entity;

import com.webshop.api.dto.request.CreateProductRequest;
import com.webshop.api.dto.request.UpdateProductRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal price;
    private String description;
    private String picturePath;

    public Product(CreateProductRequest createProductRequest) {
        this.name = createProductRequest.getName();
        this.price = createProductRequest.getPrice() == null ? BigDecimal.valueOf(0) : getPrice();
        this.description = createProductRequest.getDescription();
        this.picturePath = createProductRequest.getPicturePath();
    }

    public Product(Long id, UpdateProductRequest updateProductRequest) {
        this.id = id;
        this.name = updateProductRequest.getName();
        this.price = priceIsNull(updateProductRequest.getPrice()) ? BigDecimal.valueOf(0) : updateProductRequest.getPrice();
        this.description = updateProductRequest.getDescription();
        this.picturePath = updateProductRequest.getPicturePath();
    }
    private boolean priceIsNull(BigDecimal price){
        return price == null;
    }
}
