package com.project.Service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Repository.WalletRepository;
import com.project.Response.UserInformation;
import com.project.model.Wallet;


@Service
public class WalletService {

	@Autowired
	WalletRepository walletRepository;
	
	public Wallet addUserWallet(Wallet theWallet){
		Long userId = UserInformation.userId;
		theWallet.setWalletBalance(theWallet.getAddedAmount());
		theWallet.setUserId(userId);
		return walletRepository.save(theWallet);
		
	}
	
	public Wallet updateUserWallet(Wallet theWallet){
		Long userId = UserInformation.userId;
		Wallet userDetails=walletRepository.findByUserId(userId);
		BigDecimal walletDetails=userDetails.getWalletBalance();
		BigDecimal walletInput=theWallet.getAddedAmount();
		BigDecimal walletTotal = walletDetails.add(walletInput);
		userDetails.setWalletBalance(walletTotal);
		userDetails.setAddedAmount(walletInput);
		return walletRepository.save(userDetails);
		
	}
	public Wallet showHistory(Wallet theWallet){
		Long userId = UserInformation.userId;
		Wallet userDetails=walletRepository.findByUserId(userId);
		return  userDetails;
	}
	
	

}
