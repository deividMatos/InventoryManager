package com.project.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Product;
import com.project.product.repository.ProductRepository;

@Service
//criando a classe 
public class ProductService {
    
    @Autowired
    private final ProductRepository repository;
    //relacionando a service com a repository do produto
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product create(Product product){
        System.out.println(product.toString());
        return repository.save(product);
    }

    public Product getById(Long idProduct){
        return repository.findById(idProduct).get();
    }

    public Product update (Product product){
        return repository.save(product);
    }
    public void delete (Long idProduct){
         repository.deleteById(idProduct);
    }
}
