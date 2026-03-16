package com.meru.inventory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity (name = "INVENTORY")
public class Inventory {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")	private Integer id;
	@Column(name = "PRODUCTID")	private Integer productId;
	@Column(name = "INVENTORY")	private Integer inventory;
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
	public Integer getInventory() {
		return inventory;
	}
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}
	@Override
	public String toString() {
		return "Inventory [id=" + id + ", productId=" + productId + ", inventory=" + inventory + "]";
	}
	public Inventory(Integer id, Integer productId, Integer inventory) {
		super();
		this.id = id;
		this.productId = productId;
		this.inventory = inventory;
	}
	public Inventory(Integer productId, Integer inventory) {
		super();
		this.productId = productId;
		this.inventory = inventory;
	}
	public Inventory() {
		super();
	}


}
