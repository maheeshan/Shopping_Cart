package com.san.shoppingcartservice.repository;

import com.san.shoppingcartservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {

    void deleteProductById(Long id);
}
