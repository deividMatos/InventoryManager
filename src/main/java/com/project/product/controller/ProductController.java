package com.project.product.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Product;
import com.project.product.service.ProductService;

@RestController
public class ProductController {
    @Autowired
    ProductService service;

    @PostMapping ("/product")
    public Product create(Product product){
        return service.create(product);
    }
}
