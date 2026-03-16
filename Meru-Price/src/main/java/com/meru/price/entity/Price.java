package com.meru.price.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity (name = "PRICE")
public class Price {

	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")	private Integer id;
	@Column(name = "PRODUCTID")	private Integer productId;
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
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Price [id=" + id + ", productId=" + productId + ", price=" + price + "]";
	}
	public Price(Integer id, Integer productId, Long price) {
		super();
		this.id = id;
		this.productId = productId;
		this.price = price;
	}
	public Price(Integer productId, Long price) {
		super();
		this.productId = productId;
		this.price = price;
	}
	public Price() {
		super();
	}

	
}
