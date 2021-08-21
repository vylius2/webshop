package com.webshop.api.controller;

import com.webshop.api.dto.request.CreateProductRequest;
import com.webshop.api.dto.response.CreateProductResponse;
import com.webshop.persistance.entity.Product;
import com.webshop.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<Product> getAll(){
        return productService.getAll();
    }
    @PostMapping("/create")
    public CreateProductResponse createProduct(@Valid @RequestBody CreateProductRequest createProductRequest){
        return new CreateProductResponse(productService.create(new Product(createProductRequest)));
    }
}
