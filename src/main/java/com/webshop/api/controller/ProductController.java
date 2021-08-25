package com.webshop.api.controller;

import com.webshop.api.dto.request.CreateProductRequest;
import com.webshop.api.dto.request.UpdateProductRequest;
import com.webshop.api.dto.response.CreateProductResponse;
import com.webshop.api.dto.response.UpdateProductResponse;
import com.webshop.persistance.entity.Product;
import com.webshop.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Get all products", tags = "getAll", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully get list of products"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<Product> getAll(){
        return productService.getAll();
    }

    @ApiOperation(value = "Create product", tags = "create", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created product record"),
            @ApiResponse(code = 400, message = "Validation failed"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public CreateProductResponse create(@Valid @RequestBody CreateProductRequest createProductRequest){
        return new CreateProductResponse(productService.create(new Product(createProductRequest)));
    }

    @ApiOperation(value = "Update product", tags = "update", httpMethod = "PUT")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully update product record"),
            @ApiResponse(code = 400, message = "Validation failed"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
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

    @ApiOperation(value = "Get product by id", tags = "getById", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully get product record by id"),
            @ApiResponse(code = 404, message = "Product not found error"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public Product getById(@PathVariable("id") Long id){
        return productService.getById(id);
    }

    @ApiOperation(value = "Delete product by id", tags = "delete", httpMethod = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully delete product by id"),
            @ApiResponse(code = 404, message = "Product not found error"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
        productService.delete(id);
    }
}
