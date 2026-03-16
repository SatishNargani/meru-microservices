package com.meru.promotion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.meru.promotion.entity.Promotion;

public interface PromotionRepository extends CrudRepository<Promotion, Integer> {

	@Query(value = "select * from PROMOTION i where i.PRODUCTID = ?1", nativeQuery = true)
	Optional<Promotion> findByProductId(Integer productid);
	
}
