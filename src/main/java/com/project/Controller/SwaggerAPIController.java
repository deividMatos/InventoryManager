package com.project.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
public class SwaggerAPIController {
    @GetMapping(value = "/products")
    public List<String> getProducts() {
        List<String> productsList = new ArrayList<>();
        productsList.add("Honey");
        productsList.add("Almond");
        return productsList;
    }
    @PostMapping(value = "/products")
    public String createProduct() {
        return "Product is saved successfully";
    }
}