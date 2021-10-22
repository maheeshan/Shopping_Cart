package com.san.shoppingcartservice.service;

import com.san.shoppingcartservice.entity.Product;
import com.san.shoppingcartservice.exceptions.ResourceNotFoundException;
import com.san.shoppingcartservice.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    //get all products
    public List<Product> findAllProducts() {
        return productRepo.findAll();
    }

    //add new product
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    //update a product
    public Product updateProduct(Long id, Product product) {
        Product currentProduct = productRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Product by id:" + id + "not found"));
        currentProduct.setName(product.getName());
        currentProduct.setPrice(product.getPrice());
        currentProduct.setCategory(product.getCategory());
        currentProduct.setImageUrl(product.getImageUrl());
        return productRepo.save(currentProduct);
    }

    //delete a product
    public void deleteProductById(Long id) {
        productRepo.deleteProductById(id);
    }
}
