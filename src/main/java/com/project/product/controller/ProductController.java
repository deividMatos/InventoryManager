package com.project.product.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Product;
import com.project.product.service.ProductService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
   
    //valor em chaves é o valor que sera substutido na url (json)
    @GetMapping("/product/{idProduct}")
    //indentifiCADOR DE VARIAVEL (pathvariable)
    public Product getByID(@PathVariable("idProduct")long idProduct){
        Product response = productService.getById(idProduct);
        return response;
    }
        
    @PostMapping ("/product")
    public Product create(@RequestBody Product product){
        Product response = productService.create(product);
        return response;
    }

    @PutMapping ("/product/update")
    public Product update(@RequestBody Product product){
        Product response = productService.update(product);
        return response;
    }
  


    //@DeleteMapping
}
  // visao retorno nome(variaveis recebidas)
    // {conteudo do metodo}