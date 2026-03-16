package com.meru.inventory.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.meru.inventory.entity.Inventory;

@SpringBootTest
class InventoryServiceTest {

	@Autowired
	InventoryService inventoryService;
	
	@Test
	void testGetInventory() {
		assertEquals(12, inventoryService.getByProductId(3).getInventory());
		assertEquals(11, inventoryService.getByProductId(2).getInventory());
		assertEquals(0, inventoryService.getByProductId(10).getInventory());
	}

	@Test
	void testCountInventry() {
		try {
			assertEquals(5, inventoryService.countInventry());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Test
	void testAddInventory() {
		try {
			assertEquals(22, inventoryService.getByProductId(3).getInventory());
			inventoryService.addInventory(3, 10);
			assertEquals(32, inventoryService.getByProductId(3).getInventory());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Test
	void testDeductInventory() {
		try {
			assertEquals(13, inventoryService.getByProductId(4).getInventory());
			inventoryService.deductInventory(4, 3);
			assertEquals(10, inventoryService.getByProductId(4).getInventory());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@Test
	void testDeleteInventory() {
		assertEquals(5, inventoryService.countInventry());
		inventoryService.deleteByProductId(3);
		assertEquals(4, inventoryService.countInventry());
	}

	@Test
	void testGetByProductId() {
		Inventory inventory = inventoryService.getByProductId(9); 
		assertEquals(6, inventory.getInventory());
	}

	@Test
	void testDeleteByProductId() {
		Inventory inventory = inventoryService.getByProductId(5); 
		assertEquals(14, inventory.getInventory());
	}

}
