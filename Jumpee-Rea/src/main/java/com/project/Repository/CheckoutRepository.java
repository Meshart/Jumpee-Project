package com.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Checkout;

public interface CheckoutRepository extends JpaRepository<Checkout, Long>{

	Checkout findByUserId(Long userId);
}
