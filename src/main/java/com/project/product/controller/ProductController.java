package com.project.product.controller;


import com.project.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.model.Product;
import com.project.product.service.ProductService;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping ("/product/ {List all product}")
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }
    //valor em chaves é o valor que sera substutido na url (json)
    @GetMapping("/product/{idProduct}")
    public Product getByID(@PathVariable("idProduct")long idProduct){ //indentificador DE VARIAVEL (pathvariable)
        Product response = productService.getById(idProduct);
        return response;
    }

    @PostMapping("/product")
    public Product create(@RequestBody Product product){
        Product response = productService.create(product);
        return response;
    }

    @PutMapping ("/product")
    public Product update(@RequestBody Product product){
        Product response = productService.update(product);
        return response;
    }
  

    @DeleteMapping("/product/{idProduct}")
    public void delete(@PathVariable("idProduct")long idProduct){
        productService.delete(idProduct);
    }
}
//TODO:adicionar get list