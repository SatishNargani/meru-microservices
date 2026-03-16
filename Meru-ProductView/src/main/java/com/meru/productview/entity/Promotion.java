package com.meru.productview.entity;

public class Promotion {

	private Integer id;
	private Integer productId;
	private Long discount;
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
	
}
