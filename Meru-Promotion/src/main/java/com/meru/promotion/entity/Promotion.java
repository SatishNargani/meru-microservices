package com.meru.promotion.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Promotion {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")	private Integer id;
	@Column(name = "PRODUCTID")	private Integer productId;
	@Column(name = "DISCOUNT")	private Long discount;
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
	public Long getDiscount() {
		return discount;
	}
	public void setDiscount(Long discount) {
		this.discount = discount;
	}
	@Override
	public String toString() {
		return "Promotion [id=" + id + ", productId=" + productId + ", discount=" + discount + "]";
	}
	public Promotion(Integer id, Integer productId, Long discount) {
		super();
		this.id = id;
		this.productId = productId;
		this.discount = discount;
	}
	public Promotion(Integer productId, Long discount) {
		super();
		this.productId = productId;
		this.discount = discount;
	}
	public Promotion() {
		super();
	}
	
	
	
}
