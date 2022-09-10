package com.project.product.service;

import com.project.model.Product;
import com.project.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImplamentation implements ProductServiceInterface{

    @Autowired
    ProductRepository repository;

    @Override
    public Product getProductById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Product> getAllProduct() {
        return repository.findAll();
    }
}
