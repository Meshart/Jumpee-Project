package com.project.Service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Repository.OrderRepository;
import com.project.Repository.ProductRepository;
import com.project.Response.UserInformation;
import com.project.model.Order;
import com.project.model.Product;
import com.project.model.Wallet;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	public Order addNewOrder(Order theOrder){
		Product productDetails = productDetails(theOrder.getProductId());
		BigDecimal newprice=BigDecimal.valueOf(productDetails.getPrice()); 
		BigDecimal total = totalPrice(newprice,theOrder.getQuantity());
		stock(productDetails.getStock(), theOrder.getQuantity(), productDetails.getProduct_id());
		
		Long userId = UserInformation.userId;
		theOrder.setQuantity(theOrder.getQuantity());
		theOrder.setOrderId(theOrder.getOrderId());
		theOrder.setUserId(userId);
		theOrder.setProduct_details(productDetails.getProduct_detail());
		theOrder.setPrice(productDetails.getPrice());
		theOrder.setStatus("PENDING");
		theOrder.setTotalprice(total);
		theOrder.setProductname(productDetails.getProductname());
		
		return orderRepository.save(theOrder);
		
	}
	

	public Product productDetails(Long id) {
		Product productDetails=productRepository.findByProductId(id);
		return productDetails;
	}
	 
	public BigDecimal totalPrice(BigDecimal price, int quantity){
		BigDecimal newqty = BigDecimal.valueOf(quantity);
		return price.multiply(newqty);
		
	}
	
	public void stock(int stock, int quantity, Long id){
		int result;
		result= stock - quantity;
		Product prod= productDetails(id);
		prod.setStock(result);
		
	}


	public Order showOrder(Order theOrder) {
		Long userId = UserInformation.userId;
		Order userDetails=orderRepository.findByUserId(userId);
		return  userDetails;
	}
}
