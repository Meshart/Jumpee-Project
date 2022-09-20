package com.project.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.project.Repository.WalletRepository;
import com.project.Response.MessageResponse;
import com.project.Response.UserInformation;
import com.project.Service.WalletService;
import com.project.model.Wallet;

@RestController
public class WalletController {
	
	@Autowired
	private WalletService walletService;
	
	@Autowired
	WalletRepository walletRepository;
	
	@PostMapping("/save-wallet")
	public ResponseEntity<?> addNewWallet(@RequestBody Wallet theWallet){
		if(UserInformation.userId==null) {
			return new ResponseEntity<>(new MessageResponse("You need to login first"), HttpStatus.UNAUTHORIZED);
		}else {
			walletService.addUserWallet(theWallet);
			return new ResponseEntity<>(new MessageResponse("Success: Wallet has been added!"),HttpStatus.CREATED);
		}

	}
	
	@PutMapping("/update-wallet")
	public ResponseEntity<?> updateUserWallet(@RequestBody Wallet theWallet){
		Long userId = UserInformation.userId;
		if(userId==null) {
			return new ResponseEntity<>(new MessageResponse("You need to login first"), HttpStatus.UNAUTHORIZED);
		}else {
			walletService.updateUserWallet(theWallet);
			return new ResponseEntity<>(new MessageResponse("Success: Wallet has been updated!"),HttpStatus.CREATED);
		}

	}
	
	//Account Details of all in wallet
	@GetMapping("/all-wallet")
	public List<Wallet> findAll(){
		return walletRepository.findAll();
	}
	
	@GetMapping("/show-walletHistory")
	public ResponseEntity<?> showHistory( Wallet theWallet){
		if(UserInformation.userId==null) {
			return new ResponseEntity<>(new MessageResponse("You need to login first"), HttpStatus.UNAUTHORIZED);
		}else {
			Wallet balance= walletService.showHistory(theWallet);
			
			return  ResponseEntity.ok(balance);
		
		}


	}
}
