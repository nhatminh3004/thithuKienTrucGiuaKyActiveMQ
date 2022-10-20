package com.example.demo.entity;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;




@Component
public class ListenTopicMqService {
	
	Product product = null;
	 	
	@JmsListener(destination = "lamnhatminh")
	public void receive(Product product) {
		this.product = product;
		System.out.println(product);
	}
	public Product receiveProduct() {
		// TODO Auto-generated method stub
		return product;
	}
} 
