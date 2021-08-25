package com.webshop.api.controller;

import com.webshop.api.dto.request.CreateProductRequest;
import com.webshop.api.dto.request.UpdateProductRequest;
import com.webshop.api.dto.response.CreateProductResponse;
import com.webshop.api.dto.response.UpdateProductResponse;
import com.webshop.persistance.entity.Product;
import com.webshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<Product> getAll(){
        return productService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public CreateProductResponse create(@Valid @RequestBody CreateProductRequest createProductRequest){
        return new CreateProductResponse(productService.create(new Product(createProductRequest)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UpdateProductResponse update(@PathVariable("id") Long id,
                                        @Valid @RequestBody UpdateProductRequest updateProductRequest){
        return new UpdateProductResponse(productService.update(id, updateProductRequest));
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('USER')")
    public List<Product> getByName(@RequestParam(value = "name", required = false) String name){
        return productService.searchByName(name);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public Product getById(@PathVariable("id") Long id){
        return productService.getById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
        productService.delete(id);
    }
}
