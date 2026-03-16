package com.meru.inventory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import com.meru.inventory.entity.Inventory;

public interface InventoryRepository  extends CrudRepository<Inventory, Integer>{
	
	@Query(value = "select * from INVENTORY i where i.PRODUCTID = ?1", nativeQuery = true)
	Optional<Inventory> findByProductId(Integer productid);
	
}
