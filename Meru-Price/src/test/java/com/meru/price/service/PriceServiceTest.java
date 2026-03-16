package com.meru.price.service;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.meru.price.entity.Price;

@SpringBootTest
class PriceServiceTest {
	
	@Autowired
	PriceService priceService;

	@Test
	void testGetPrice() {
		assertEquals(110, priceService.getPrice(1).getPrice()); 
	}

	@Test
	void testAddPrice() {
		assertEquals(5, priceService.count());
		priceService.createPrice(new Price(6, (long)110));
		assertEquals(6, priceService.count());
	}

	@Test
	void testUpdatePrice() {
		assertEquals(50, priceService.getPrice(2).getPrice()); 
		priceService.updatePrice(2, (long)150);
		assertEquals(150, priceService.getPrice(2).getPrice()); 
	}

	@Test
	void testDeletePrice() {
		assertEquals(5, priceService.count()); 
		priceService.deletePrice(1);
		assertEquals(4, priceService.count()); 
	}

	@Test
	void testDeleteByProductId() {
		fail("Not yet implemented");
	}

}
