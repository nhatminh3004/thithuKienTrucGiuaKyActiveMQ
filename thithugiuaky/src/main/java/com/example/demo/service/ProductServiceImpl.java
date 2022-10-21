package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductServiceImpl implements  ProductService{
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getOneProduct(int id) {
        Optional<Product> product=productRepository.findById(id);
        return product.get();
    }

    @Override
    public Product addProduct(Product p) {
        return productRepository.save(p);
    }

    @Override
    public void deleteProductById(int id) {
        productRepository.deleteById(id);
    }
}
