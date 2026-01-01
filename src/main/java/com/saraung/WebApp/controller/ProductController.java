package com.saraung.WebApp.controller;

import com.saraung.WebApp.model.Product;
import com.saraung.WebApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{prodId}")
    public Product getProductById(@PathVariable int prodId){
        return productService.getProductById(prodId);
    }

    @PostMapping("/products")
    public String addProduct(@RequestBody Product product){
        System.out.println(product);
        return productService.addProduct(product);
    }
    @PutMapping("/products")
    public  String updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }
    @DeleteMapping("/products/{prodId}")
    public String deleteProduct(@PathVariable int prodId){
        return productService.deleteProduct(prodId);
    }
}
