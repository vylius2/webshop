package com.webshop.service;

import com.webshop.persistance.repository.ProductRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public boolean existsById(Long id){
        return productRepository.existsById(id);
    }


}
