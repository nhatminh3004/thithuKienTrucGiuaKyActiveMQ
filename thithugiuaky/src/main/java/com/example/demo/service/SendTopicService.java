package com.example.demo.service;


import com.example.demo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;



@Service
public class SendTopicService {

	@Autowired
	private JmsTemplate jTemplate;

	String topic = "jpa-topic";

	public String SendProduct(Product product) {
		jTemplate.convertAndSend("lamnhatminh", product);
		System.out.println("Send secesfull : " + product.toString());
		return "Send secesfull " + product.toString();
	}
}
