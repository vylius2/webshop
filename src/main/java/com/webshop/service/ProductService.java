package com.webshop.service;

import com.webshop.api.dto.request.UpdateProductRequest;
import com.webshop.exception.ProductNotFound;
import com.webshop.persistance.entity.Product;
import com.webshop.persistance.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public boolean existsById(Long id){
        return productRepository.existsById(id);
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Product create(Product product){
        return productRepository.save(product);
    }

    public Product update(Long productId, UpdateProductRequest updateProductRequest) {
        return productRepository.save(new Product(productId, updateProductRequest));
    }
    public Product getById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFound(id));
    }

    public List<Product> searchByName(String name){
        if (name == null)
            return productRepository.findAll();
        return productRepository.getProductsByNameContainingIgnoreCase(name);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
