package com.project.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="checkout")
public class Checkout {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long checkoutId;
	private Long orderId;
	private Long userId;
	private Long productId;
	private String status;
	private Long price;
	private BigDecimal totalprice;
	private String product_details;
	private String productname;
	
	public Checkout() {
	
	}
	

	public Checkout(Long checkoutId, Long orderId, Long userId, Long productId, String status, Long price,
			BigDecimal totalprice, String product_details, String productname) {
		super();
		this.checkoutId = checkoutId;
		this.orderId = orderId;
		this.userId = userId;
		this.productId = productId;
		this.status = status;
		this.price = price;
		this.totalprice = totalprice;
		this.product_details = product_details;
		this.productname = productname;
	}



	public Long getProductId() {
		return productId;
	}


	public void setProductId(Long productId) {
		this.productId = productId;
	}


	public Long getCheckoutId() {
		return checkoutId;
	}

	public void setCheckoutId(Long checkoutId) {
		this.checkoutId = checkoutId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public BigDecimal getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}

	public String getProduct_details() {
		return product_details;
	}

	public void setProduct_details(String product_details) {
		this.product_details = product_details;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	

	@Override
	public String toString() {
		return "Checkout [checkoutId=" + checkoutId + ", orderId=" + orderId + ", userId=" + userId + ", productId="
				+ ", status=" + status + ", price=" + price + ", totalprice=" + totalprice
				+ ", product_details=" + product_details + ", productname=" + productname + "]";
	}

}
