package com.example.demo.restcontroller;

import com.example.demo.service.ListenTopicMqService;
import com.example.demo.entity.Product;
import com.example.demo.service.SendTopicService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductRestController {
    @Autowired
    private ProductService productService;
    //activemq topic
    @Autowired
    private SendTopicService sendTopicService;
    @Autowired
    private ListenTopicMqService listenTopicMqService;
    //
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
    //cau 2 : gui product toi topic activemq
    @GetMapping ("/sendProductToTopic/{idproduct}")
    public String sendProductToTopic(@PathVariable int idproduct){
        Product p = productService.getOneProduct(idproduct);
            return sendTopicService.SendProduct(p);
    }
    @GetMapping("/nhanProductFromTopic")
    public  Product nhanProductFromTopic(){
        return listenTopicMqService.receiveProduct();
    }

}
