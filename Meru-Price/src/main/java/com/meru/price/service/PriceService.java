package com.meru.price.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.meru.price.entity.Price;
import com.meru.price.repository.PriceRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class PriceService {
	@Autowired
	private EurekaClient eurekaClient;
	@Autowired
	private RestTemplateBuilder restTemplateBuilder;
	@Autowired
	PriceRepository priceRepository;
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	@LoadBalanced
	RestTemplate restTemplate(){
		return restTemplateBuilder.build();
	}
	
	public Price getPrice(Integer productId){
		Optional<Price> price = priceRepository.findByProductId(productId);
		if(price.isPresent()) {
			return price.get();
		}
		return null;
	}
	
	public long count() {
		return priceRepository.count();
	}
	
	public Price createPrice(Price price) {
		return priceRepository.save(price);
	}
	
	@Transactional
	@HystrixCommand(fallbackMethod = "updatePrice_defaultMessge")
	public String updatePrice(Integer productId, Long price) {
		if(price > 0) {
			Optional<Price> oprice = priceRepository.findByProductId(productId);
			if(oprice.isPresent()) {
				Price price2save = oprice.get();
				price2save.setPrice(price);
				price2save = priceRepository.save(price2save);
//				update product view
				restTemplate.put("http://meru-zuul-gateway/api/productview/products/"+productId+"/productview/updateprice", price2save);
				
				return price2save.toString();
			}
		}
		return null;
	}
	
	public String updatePrice_defaultMessge(Integer productId, Long price) {
		return "Some of the services might be down so operation can not be performed. Please try after some time.";
	}
	
	public Price deletePrice(Integer id) {
		Optional<Price> oprice = priceRepository.findByProductId(id);
		if(oprice.isPresent()) {
			Price price = oprice.get();
			priceRepository.delete(price);
			return price;
		}
		return null;
	}
}
