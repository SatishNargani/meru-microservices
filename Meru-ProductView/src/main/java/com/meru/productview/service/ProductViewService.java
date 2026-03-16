package com.meru.productview.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.meru.productview.entity.ProductView;
import com.meru.productview.entity.Promotion;
import com.meru.productview.repository.ProductViewRepository;
import com.netflix.discovery.EurekaClient;

@Service
public class ProductViewService {

	@Value("${server.port}")
	String serverport;
	@Autowired
	private EurekaClient eurekaClient;
	@Autowired
	private RestTemplateBuilder restTemplateBuilder;
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	@LoadBalanced
	RestTemplate restTemplate(){
		return restTemplateBuilder.build();
	}
	

	@Autowired
	ProductViewRepository productViewRepository;
	
	public Iterable<ProductView> getProductAllView(){
		Iterable<ProductView> productView = productViewRepository.findAll();
		return productView;
	}
	
	public String testRibbon() {
		return "Product View Service running on port no "+serverport;
	}

	
	public ProductView getProductView(Integer productId){
		Optional<ProductView> productView = productViewRepository.findByProductId(productId);
		if(productView.isPresent()) {
			return productView.get();
		}
		return null;
	}
	
	public ProductView addProductView(ProductView ProductView) {
		ProductView savedProductView = productViewRepository.save(ProductView);
		return savedProductView;
	}
	
	public ProductView updateProductDesc(Integer productId, String productDesc) {
		Optional<ProductView> oproductView = productViewRepository.findByProductId(productId);
		if(oproductView.isPresent()) {
			ProductView productView = oproductView.get();
			productView.setProductDescription(productDesc);
			return productViewRepository.save(productView);
		}
		return null;
	}
	
	public long count() {
		return productViewRepository.count();
	}
	
	
	
	public ProductView updateProductPrice(Integer productId, Long price) {
		Optional<ProductView> oproductView = productViewRepository.findByProductId(productId);
		if(oproductView.isPresent()) {
			ProductView productView = oproductView.get();
			productView.setPrice(price); 
			return productViewRepository.save(productView);
		}
		return null;
	}
	
	public ProductView updateProductInventory(Integer productId, int inventory) {
		Optional<ProductView> oproductView = productViewRepository.findByProductId(productId);
		if(oproductView.isPresent()) {
			ProductView productView = oproductView.get();
			productView.setInventory(inventory); 
			return productViewRepository.save(productView);
		}
		return null;
	}
	
	public ProductView deleteByProductId(Integer productId) {
		Optional<ProductView> oproductView = productViewRepository.findByProductId(productId);
		if(oproductView.isPresent()) {
			ProductView productView = oproductView.get();
			productViewRepository.delete(productView);
			return productView;
		}
		return null;
	}
	
	public String getProductPriceByApplyPromotion(Integer productId) {
		try {
			Optional<ProductView> productView = productViewRepository.findByProductId(productId);
			if (productView.isPresent()) {
				ProductView view = productView.get();
				String url = "http://meru-zuul-gateway/api/promotion/products/" + productId + "/promotion";
				System.out.println("URL : "+url);
				Promotion promotion = null;
				try {
					promotion = restTemplate.getForObject(url, Promotion.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (promotion != null) {
					return "Product price is: INR " + view.getPrice() + ", Promotion Discount applied: INR "
							+ promotion.getDiscount() + ". Final price after discount: INR "
							+ (view.getPrice() - promotion.getDiscount());
				} else {
					return "No promotion found to be applied. Product price is: INR " + view.getPrice();
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
