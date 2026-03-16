package com.meru.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "PRODUCT")
public class Product {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "PRODUCTID")	private Integer productId;
	@Column(name = "PRODUCTNAME")	private String productName;
	@Column(name = "PRODUCTDESCRIPTION")		private String productDescription;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productDescription="
				+ productDescription + "]";
	}
	public Product(Integer productId, String productName, String productDescription) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
	}
	public Product(String productName, String productDescription) {
		super();
		this.productName = productName;
		this.productDescription = productDescription;
	}
	public Product() {
		super();
	}
	
	
	
}
