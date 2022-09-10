package com.project.product.service;

import com.project.model.Product;

import java.util.List;

public interface ProductServiceInterface {
    public Product getProductById(Long id);
    public List<Product> getAllProduct();
}
