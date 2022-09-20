package com.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Response.MessageResponse;
import com.project.Response.UserInformation;
import com.project.Service.CheckoutService;
import com.project.model.Checkout;

@RestController
public class CheckoutController {
	
	@Autowired
	CheckoutService checkoutService;
	
	@PostMapping("/checkout")
	public ResponseEntity<?> addNewOrder(@RequestBody Checkout theCheckout){
		if(UserInformation.userId==null) {
			return new ResponseEntity<>(new MessageResponse("You need to login first"), HttpStatus.UNAUTHORIZED);
		}else {
			Checkout result = checkoutService.checkout(theCheckout);
				if(result==null) {
					return new ResponseEntity<>(new MessageResponse("Error:Not enough wallet balance"), HttpStatus.BAD_REQUEST);
				}else {
				return new ResponseEntity<>(new MessageResponse("Success: Your order has been checked out"), HttpStatus.OK);
				}
	}
	}
	
	@GetMapping("/show-checkout")
	public ResponseEntity<?> showAddress( Checkout theCheckout){
		if(UserInformation.userId==null) {
			return new ResponseEntity<>(new MessageResponse("You need to login first"), HttpStatus.UNAUTHORIZED);
		}else {
			Checkout check= checkoutService.showCheckout(theCheckout);
			return  ResponseEntity.ok(check);

		}
	}
}
