package com.meru.promotion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meru.promotion.entity.Promotion;
import com.meru.promotion.exception.PromotionNotFound;
import com.meru.promotion.service.PromotionService;
@RestController
@RefreshScope
public class PromotionController {
	@Value("${filename}")
	private String filename;
	@Value("${server.port}")
	String serverport;
	
	@Autowired
	PromotionService service;
	
	@GetMapping("/test")
	public String test() {
		return filename + " Service is working on port : "+serverport;
	}
	
	@GetMapping("/promotions")
	public Iterable<Promotion> getAllPromotion(){
		Iterable<Promotion> promotions = service.getAllPromotion();
		if(!promotions.iterator().hasNext()) {
			throw new PromotionNotFound("Promotion not found.");
		}
		return promotions;
	}
	@GetMapping("/promotions/{id}")
	public Promotion getPromotion(@PathVariable Integer id){
		Promotion promotion = service.getPromotion(id);
		if(promotion == null) {
			throw new PromotionNotFound("Promotion not found.");
		}
		return promotion;
	}
	@GetMapping("/products/{id}/promotion")
	public Promotion getPromotionByProduct(@PathVariable Integer id){
		System.out.println("Promotion is called");
		Promotion promotion = service.getPromotionByProductId(id);
		if(promotion == null) {
			throw new PromotionNotFound("Promotion not found.");
		}
		System.out.println(promotion.toString());
		return promotion;
	}
	@PostMapping("/promotions")
	public Promotion addPromotion(@RequestBody Promotion promotion){
		return service.createPromotion(promotion);
	}
	@PutMapping("/promotions/{id}")
	public Promotion updatePromotion(@PathVariable Integer id, @RequestBody Promotion promotion){
		Promotion updatepromotion = service.updatePromotion(id, promotion);
		if(updatepromotion == null) {
			throw new PromotionNotFound("Promotion not found.");
		}
		return updatepromotion;
	}
	@DeleteMapping("/promotions/{id}")
	public Promotion deletePromotion(@PathVariable Integer id){
		Promotion promotion = service.deletePromotion(id);
		if(promotion == null) {
			throw new PromotionNotFound("Promotion not found.");
		}
		return promotion;
	}
	@DeleteMapping("/products/{id}/promotion")
	public Promotion deletePromotionByProductId(@PathVariable Integer id){
		Promotion promotion = service.deletePromotionByProductId(id);
		if(promotion == null) {
			throw new PromotionNotFound("Promotion not found.");
		}
		return promotion;
	}
}
