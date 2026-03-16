package com.meru.promotion.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meru.promotion.entity.Promotion;
import com.meru.promotion.repository.PromotionRepository;

@Service
public class PromotionService {
	
	@Autowired
	PromotionRepository promotionRepository;
	
	public long count() {
		return promotionRepository.count();
	}
	public Iterable<Promotion> getAllPromotion() {
		
		Iterable<Promotion> promotions = promotionRepository.findAll();
		if(!promotions.iterator().hasNext()) {
			return null;
		}
		return promotions;
	}
	public Promotion getPromotion(Integer id) {
		Optional<Promotion> promotion = promotionRepository.findById(id);
		if(promotion.isPresent()) {
			return promotion.get();
		}
		return null;
	}
	public Promotion getPromotionByProductId(Integer id) {
		Optional<Promotion> promotion = promotionRepository.findByProductId(id);
		if(promotion.isPresent()) {
			return promotion.get();
		}
		return null;
	}
	public Promotion createPromotion(Promotion promotion) {
		return promotionRepository.save(promotion);
	}
	public Promotion updatePromotion(Integer id, Promotion promotion) {
		Optional<Promotion> opromotion = promotionRepository.findById(id);
		if(opromotion.isPresent()) {
			Promotion updatepromotion = opromotion.get();
			updatepromotion.setDiscount(promotion.getDiscount()); 
			return promotionRepository.save(updatepromotion);
		}
		return null;
	}
	public Promotion updatePromotionnByProductId(Integer id, Promotion promotion) {
		Optional<Promotion> opromotion = promotionRepository.findByProductId(id);
		if(opromotion.isPresent()) {
			Promotion updatepromotion = opromotion.get();
			updatepromotion.setDiscount(promotion.getDiscount()); 
			return promotionRepository.save(updatepromotion);
		}
		return null;
	}
	public Promotion deletePromotion(Integer id) {
		Optional<Promotion> opromotion = promotionRepository.findById(id);
		if(opromotion.isPresent()) {
			Promotion promotion = opromotion.get();
			promotionRepository.delete(promotion);
			return promotion;
		}
		return null;
	}
	public Promotion deletePromotionByProductId(Integer id) {
		Optional<Promotion> opromotion = promotionRepository.findByProductId(id);
		if(opromotion.isPresent()) {
			Promotion promotion = opromotion.get();
			promotionRepository.delete(promotion);
			return promotion;
		}
		return null;
	}
	

}
