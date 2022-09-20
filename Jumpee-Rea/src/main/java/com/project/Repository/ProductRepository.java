package com.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	Product findByProductId(Long id);
}
