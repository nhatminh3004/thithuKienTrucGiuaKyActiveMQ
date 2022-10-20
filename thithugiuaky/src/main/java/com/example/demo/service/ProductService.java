package com.example.demo.service;

import com.example.demo.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProduct();
    public Product getOneProduct(int id);
    public Product addProduct(Product p);
    public void deleteProductById(int id);
}
