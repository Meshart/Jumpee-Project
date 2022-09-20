package com.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long>{
	 Wallet findByUserId(Long id);


}
