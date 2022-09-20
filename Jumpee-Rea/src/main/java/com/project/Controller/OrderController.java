package com.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Repository.OrderRepository;
import com.project.Response.MessageResponse;
import com.project.Response.UserInformation;
import com.project.Service.OrderService;
import com.project.model.Order;
import com.project.model.Wallet;

@RestController
public class OrderController {
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	OrderService orderService;
	
	@PostMapping("/save-order")
	public ResponseEntity<?> addNewOrder(@RequestBody Order theOrder){
		if(UserInformation.userId==null) {
			return new ResponseEntity<>(new MessageResponse("You need to login first"), HttpStatus.UNAUTHORIZED);
		}else {
			orderService.addNewOrder(theOrder);
			return new ResponseEntity<>(new MessageResponse("Success: Order has been added!"),HttpStatus.CREATED);
		}

	}
	
	@GetMapping("/show-order")
	public ResponseEntity<?> showHistory( Order theOrder){
		if(UserInformation.userId==null) {
			return new ResponseEntity<>(new MessageResponse("You need to login first"), HttpStatus.UNAUTHORIZED);
		}else {
			Order show= orderService.showOrder(theOrder);
			
			return  ResponseEntity.ok(show);
		
		}
	}
}
