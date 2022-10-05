package com.project.product.controller;


import com.project.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.project.model.Product;
import com.project.product.service.ProductService;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping ("/product/list")
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }
    //valor em chaves é o valor que sera substutido na url (json)
    @GetMapping("/product/{idProduct}")
    public Product getByID(@PathVariable("idProduct")long idProduct){ //indentificador DE VARIAVEL (pathvariable)
        try {
            return productService.getById(idProduct);
        } catch (NoSuchElementException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado", exception);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Problema na requisicao", exception);
        }

    }

    @PostMapping("/product")
    public Product create(@RequestBody Product product){
        try {
            Product response = productService.create(product);
            return response;
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Problema na requisicao", exception);
        }
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
//    @GetMapping("/product/list")
//    public List<Product> getAll(){
//        List<Product> produtos = productService.getAll();
//        if(!produtos.isEmpty()){
//            return produtos;
//        }else{
//            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
//        }
//    }
}
//TODO:adicionar get list