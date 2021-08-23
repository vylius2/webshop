package com.webshop.api.controller;

import com.webshop.api.dto.request.CreateProductRequest;
import com.webshop.api.dto.request.UpdateProductRequest;
import com.webshop.api.dto.response.CreateProductResponse;
import com.webshop.api.dto.response.UpdateProductResponse;
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

    @PutMapping("/update/{id}")
    public UpdateProductResponse update(@PathVariable("id") Long id,
                                        @Valid @RequestBody UpdateProductRequest updateProductRequest){
        return new UpdateProductResponse(productService.update(id, updateProductRequest));
    }
    //GET BY NAME
    @GetMapping("/get/{name}")
    public List<Product> getByName(@PathVariable("name") String name){
        return productService.searchByName(name);
    }
    //GET BY ID

    //DELETE
}
