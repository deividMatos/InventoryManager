package com.project.product.controller;

import com.project.model.Product;
import com.project.product.service.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    ProductServiceInterface service;

    @GetMapping("/product/{idProduto}")
    public Product getByid(@PathVariable Long idProduto){
        return service.getProductById(idProduto);
    }
}
