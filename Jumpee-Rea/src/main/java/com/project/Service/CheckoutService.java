package com.project.Service;


import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Repository.CheckoutRepository;
import com.project.Repository.OrderRepository;
import com.project.Repository.ProductRepository;
import com.project.Repository.WalletRepository;
import com.project.Response.UserInformation;
import com.project.model.Checkout;
import com.project.model.Order;
import com.project.model.Product;
import com.project.model.Wallet;


@Service
public class CheckoutService {
	
	@Autowired
	CheckoutRepository checkoutRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	WalletRepository walletRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	public Checkout checkout(Checkout theCheckout){
		
		Order orderDetails=orderDetails(theCheckout.getOrderId());
		Long userId = UserInformation.userId;
		theCheckout.setOrderId(theCheckout.getOrderId());
		theCheckout.setUserId(userId);
		theCheckout.setProductId(theCheckout.getProductId());
		theCheckout.setProduct_details(orderDetails.getProduct_details());
		theCheckout.setPrice(orderDetails.getPrice());
		theCheckout.setStatus("PAID");
		theCheckout.setTotalprice(orderDetails.getTotalprice());
		theCheckout.setProductname(orderDetails.getProductname());
		Boolean mesg= updateWalletBalance(theCheckout.getTotalprice(),theCheckout.getUserId(),theCheckout);
		
		if(mesg==true) {
			return checkoutRepository.save(theCheckout);
		}
		else {
			return null;
		}
		
		
	}
	
	public Order orderDetails(Long id) {
		Order productDetails=orderRepository.findByOrderId(id);
		return productDetails;
	}
	

	public Boolean updateWalletBalance(BigDecimal totalprice,Long id,Checkout theCheckout){
		Wallet walletDetails=walletRepository.findByUserId(id);
		
		if(walletDetails.getWalletBalance().compareTo(theCheckout.getTotalprice())==1) {
			
			BigDecimal result = (walletDetails.getWalletBalance()).subtract(totalprice);
			walletDetails.setWalletBalance(result);
			walletRepository.save(walletDetails);
			//System.out.println("te may laman");
			return true;
		
		}else {
			//System.out.println("te walang laman");
			return false;
		}
		

	}
	public Checkout showCheckout(Checkout theCheckout) {
		Long userId = UserInformation.userId;
		Checkout userDetails=checkoutRepository.findByUserId(userId);
		return  userDetails;
}
	
}
