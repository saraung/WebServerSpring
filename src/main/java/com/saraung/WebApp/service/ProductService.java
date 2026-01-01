package com.saraung.WebApp.service;

import com.saraung.WebApp.model.Product;
import com.saraung.WebApp.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

//    List<Product>products=new ArrayList<>(Arrays.asList(
//            new Product(101,"Iphone",50000),
//            new Product(102,"Sony Camera",30000),
//            new Product(103,"mac book",80000)));

    public List<Product> getProducts(){

        return productRepo.findAll();
    }

    public Product getProductById(int prodId) {
        return productRepo.findById(prodId).orElse(new Product());
    }

    public String addProduct(Product prod){
        productRepo.save(prod);
        return "product added";
    }

    public String updateProduct(Product updatedProduct) {

        productRepo.save(updatedProduct);

        return "product updated";
    }

    public String deleteProduct(int prodId) {

        productRepo.deleteById(prodId);

        return "product deleted";
    }

}
