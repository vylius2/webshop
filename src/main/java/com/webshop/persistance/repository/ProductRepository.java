package com.webshop.persistance.repository;

import com.webshop.persistance.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
