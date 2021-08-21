package com.webshop.service;

import com.webshop.persistance.entity.Product;
import com.webshop.persistance.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

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


}
