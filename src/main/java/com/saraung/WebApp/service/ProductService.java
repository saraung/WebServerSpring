package com.saraung.WebApp.service;

import com.saraung.WebApp.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    List<Product>products=new ArrayList<>(Arrays.asList(
            new Product(101,"Iphone",50000),
            new Product(102,"Sony Camera",30000),
            new Product(103,"mac book",80000)));

    public List<Product> getProducts(){
        return products;
    }

    public Product getProductById(int prodId) {
        return products.stream().filter(p->p.getProdId()==prodId).findFirst().get();
    }

    public String addProduct(Product prod){
        products.add(prod);
        return "product added";
    }

    public String updateProduct(Product updatedProduct) {

        Product existingProduct = products.stream()
                .filter(p -> p.getProdId() == updatedProduct.getProdId())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setProdName(updatedProduct.getProdName());
        existingProduct.setPrice(updatedProduct.getPrice());

        return "product updated";
    }

    public String deleteProduct(int prodId) {

        boolean removed = products.removeIf(p -> p.getProdId() == prodId);

        if (!removed) {
            throw new RuntimeException("Product not found");
        }

        return "product deleted";
    }

}
