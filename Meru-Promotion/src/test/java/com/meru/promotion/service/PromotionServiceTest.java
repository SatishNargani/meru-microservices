package com.meru.promotion.service;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.meru.promotion.entity.Promotion;



@SpringBootTest
class PromotionServiceTest {
	
	@Autowired
	PromotionService service;

	@Test
	void testCount() {
		assertEquals(5, service.count()); 
	}

	@Test
	void testGetAllPromotion() {
		Iterable<Promotion> promotions = service.getAllPromotion();
		for(Promotion promotion : promotions) {
			System.out.println(promotion);
		}
	}

	@Test
	void testGetPromotion() {
		Promotion promotion = service.getPromotion(2);
		assertEquals(20, promotion.getDiscount()); 
	}

	@Test
	void testGetPromotionByProductId() {
		Promotion promotion = service.getPromotionByProductId(2);
		assertEquals(10, promotion.getDiscount()); 
	}

	@Test
	void testCreatePromotion() {
		assertEquals(0, service.count()); 
		service.createPromotion(new Promotion(2, (long)10));
		service.createPromotion(new Promotion(3, (long)20));
		service.createPromotion(new Promotion(4, (long)30));
		service.createPromotion(new Promotion(5, (long)25));
		service.createPromotion(new Promotion(6, (long)35));
		assertEquals(5, service.count()); 
	}

	@Test
	void testUpdatePromotion() {
		Promotion promotion = service.getPromotion(2);
		assertEquals(20, promotion.getDiscount());
		service.updatePromotion(2, new Promotion(1, (long)31));
		promotion = service.getPromotion(2);
		assertEquals(31, promotion.getDiscount());

	}

	@Test
	void testUpdatePromotionnByProductId() {
		Promotion promotion = service.getPromotionByProductId(2);
		assertEquals(10, promotion.getDiscount()); 
		service.updatePromotionnByProductId(2, new Promotion(1, (long)21));
		promotion = service.getPromotionByProductId(2);
		assertEquals(21, promotion.getDiscount());
	}

	@Test
	void testDeletePromotion() {
		assertEquals(5, service.count()); 
		service.deletePromotion(1);
		assertEquals(4, service.count()); 
	}

	@Test
	void testDeletePromotionByProductId() {
		assertEquals(4, service.count()); 
		service.deletePromotionByProductId(2);
		assertEquals(4, service.count()); 
	}

}
