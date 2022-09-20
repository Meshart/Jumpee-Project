package com.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Order;
import com.project.model.Wallet;

public interface OrderRepository extends JpaRepository<Order, Long>{


	Order findByOrderId(Long id);

	Order findByUserId(Long userId);

	
}
