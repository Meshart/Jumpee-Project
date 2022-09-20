package com.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="prodlist")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	private int stock;
	private String productname;
	private Long price;
	private String product_details;
	
	public Product() {
		
	}
	
	public Product(long product_id, int stock, String productname, Long price, String product_details) {
		super();
		this.productId = product_id;
		this.stock = stock;
		this.productname = productname;
		this.price = price;
		this.product_details = product_details;
	}

	public long getProduct_id() {
		return productId;
	}

	public void setProduct_id(long product_id) {
		this.productId = product_id;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getProduct_detail() {
		return product_details;
	}

	public void setProduct_detail(String product_details) {
		this.product_details = product_details;
	}

	@Override
	public String toString() {
		return "Product [product_id=" + productId + ", stock=" + stock + ", productname=" + productname + ", price="
				+ price + ", product_detail=" + product_details + "]";
	}
	
	

}
