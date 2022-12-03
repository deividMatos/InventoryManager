package com.project.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Product;
import com.project.product.repository.ProductRepository;
import org.webjars.NotFoundException;

import javax.persistence.EntityExistsException;
import java.util.List;

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
        if(!repository.existsById(product.getId()))
            return repository.save(product);
        else{
            throw new EntityExistsException("product already exists");
        }
    }

    public Product getById(Long idProduct){
        return repository.findById(idProduct).get();
    }

    public Product update (Product product){
        if(repository.existsById(product.getId())){
            return repository.save(product);
        }else{
            throw new NotFoundException("Product not found");
        }
    }
    public void delete (Long idProduct){
         repository.deleteById(idProduct);
    }
    public List<Product> getAllProduct() {
        return repository.findAll();
    }
}
