package com.meru.price.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.meru.price.entity.Price;

public interface PriceRepository extends CrudRepository<Price, Integer>{
	@Query(value = "select * from PRICE i where i.PRODUCTID = ?1", nativeQuery = true)
	Optional<Price> findByProductId(Integer productid);
}
