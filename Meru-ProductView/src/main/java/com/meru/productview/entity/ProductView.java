package com.meru.productview.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity (name = "PRODUCTVIEW")
public class ProductView {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")	private Integer id;
	@Column(name = "PRODUCTID")	private Integer productId;
	@Column(name = "PRODUCTNAME")	private String productName;
	@Column(name = "PRODUCTDESCRIPTION")		private String productDescription;
	@Column(name = "INVENTORY")	private Integer inventory;
	@Column(name = "PRICE")	private Long price;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public Integer getInventory() {
		return inventory;
	}
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "ProductView [id=" + id + ", productId=" + productId + ", productName=" + productName
				+ ", productDescription=" + productDescription + ", inventory=" + inventory + ", price=" + price + "]";
	}
	public ProductView(Integer id, Integer productId, String productName, String productDescription, Integer inventory,
			Long price) {
		super();
		this.id = id;
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.inventory = inventory;
		this.price = price;
	}
	public ProductView(Integer productId, String productName, String productDescription, Integer inventory,	Long price) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.inventory = inventory;
		this.price = price;
	}
	public ProductView() {
		super();
	}
	
	
}
