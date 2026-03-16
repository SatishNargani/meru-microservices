package com.meru.product.repository;

import org.springframework.data.repository.CrudRepository;

import com.meru.product.entity.Product;

public interface ProductRepository  extends CrudRepository<Product, Integer>{

}
