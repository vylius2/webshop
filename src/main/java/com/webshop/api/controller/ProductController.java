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

@CrossOrigin
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
    @GetMapping("/search")
    public List<Product> getByName(@RequestParam(value = "name", required = false) String name){
        return productService.searchByName(name);
    }
    @GetMapping("/get/{id}")
    public Product getById(@PathVariable("id") Long id){
        return productService.getById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        productService.delete(id);
    }
}
