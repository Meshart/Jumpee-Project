package com.project.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="wallet")
public class Wallet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long walletId;
	private BigDecimal walletBalance;
	private BigDecimal addedAmount;
	private Long userId;
	
	public Wallet() {
		
	}
	


	public Wallet(Long walletId, BigDecimal walletBalance, BigDecimal addedAmount, Long userId) {
		super();
		this.walletId = walletId;
		this.walletBalance = walletBalance;
		this.addedAmount = addedAmount;
		this.userId = userId;
	}




	public Long getWalletId() {
		return walletId;
	}

	public void setWalletId(Long walletId) {
		this.walletId = walletId;
	}
	

	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public BigDecimal getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(BigDecimal walletBalance) {
		this.walletBalance = walletBalance;
	}


	public BigDecimal getAddedAmount() {
		return addedAmount;
	}


	public void setAddedAmount(BigDecimal addedAmount) {
		this.addedAmount = addedAmount;
	}

	@Override
	public String toString() {
		return "Wallet [walletId=" + walletId + ", walletBalance=" + walletBalance + ", addedAmount=" + addedAmount
				+ ", userId=" + userId + "]";
	}
	
}
