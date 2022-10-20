package com.example.demo.restcontroller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductRestController {
    @Autowired
    private ProductService productService;
    //Lấy tất cả product
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return  productService.getAllProduct();
    }
    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable int id){
        return productService.getOneProduct(id);
    }
    //thêm product mới
    @PostMapping("/addproduct")
    public Product addNewProduct(@RequestBody Product p){
        return  productService.addProduct(p);
    }

}
