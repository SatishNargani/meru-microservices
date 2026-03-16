package com.meru.productview.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.meru.productview.entity.ProductView;

public interface ProductViewRepository  extends CrudRepository<ProductView, Integer>{

	@Query(value = "select * from PRODUCTVIEW i where i.PRODUCTID = ?1", nativeQuery = true)
	Optional<ProductView> findByProductId(Integer productid);

}
