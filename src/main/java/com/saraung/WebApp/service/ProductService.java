package com.saraung.WebApp.service;

import com.saraung.WebApp.config.ImageKitService;
import com.saraung.WebApp.entity.Product;
import com.saraung.WebApp.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;
    @Autowired
    private ImageKitService imageKitService;


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



    public String addProduct(Product prod, MultipartFile imageFile) {
        String url = imageKitService.upload(imageFile);
        prod.setImageUrl(url);
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

    public List<Product> searchProducts(String keyword) {
        return productRepo.searchProducts(keyword);
    }
}
