package com.webshop.persistance.repository;

import com.webshop.persistance.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsById(Long id);
    List<Product> getProductsByNameContainingIgnoreCase(String name);

}
