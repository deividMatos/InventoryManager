package com.project.product.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.project.model.Product;
import com.project.product.service.ProductService;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
   
    //valor em chaves é o valor que sera substutido na url (json)
    @GetMapping("/product/{idProduct}")
    public Product getByID(@PathVariable("idProduct")long idProduct){    //indentifiCADOR DE VARIAVEL (pathvariable)
        Product response = productService.getById(idProduct);
        return response;
    }

    @PostMapping("/product/insert")
    public Product create(@RequestBody Product product){
        Product response = productService.create(product);
        return response;
    }

    @PutMapping ("/product/update")
    public Product update(@RequestBody Product product){
        Product response = productService.update(product);
        return response;
    }
  

    @DeleteMapping("/product/{idProduct}")
    public void delete(@PathVariable("idProduct")long idProduct){
        productService.delete(idProduct);
    }
}
  // visao retorno nome(variaveis recebidas)
    // {conteudo do metodo}
//TODO:adicionar get list