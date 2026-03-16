package com.meru.price.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meru.price.entity.Price;
import com.meru.price.exception.ProductNotFoundException;
import com.meru.price.service.PriceService;

@RestController
@RefreshScope
public class PriceController {
	@Value("${filename}")
	private String filename;
	@Value("${server.port}")
	String serverport;
	
	@Autowired
	PriceService priceService;
	
	@GetMapping("/test")
	public String test() {
		return filename + " Service is working on port : "+serverport;
	}
	
	@GetMapping("/products/{id}/price")
	public Price getPrice(@PathVariable Integer id) {
		Price price = priceService.getPrice(id);
		if(price == null) {
			throw new ProductNotFoundException("Product "+id+" is not found.");
		}
		return price;
	}
	@PostMapping("/products/{id}/price")
	public ResponseEntity<String>  createPrice(@PathVariable Integer id) {
		Price createdPrice = priceService.createPrice(new Price(id, (long)0));
		ResponseEntity<String> response = new ResponseEntity<>(createdPrice.toString(), HttpStatus.OK);
		return response;
	}
	@PutMapping("/products/{id}/price")
	public ResponseEntity<String> updatePrice(@PathVariable Integer id, @RequestBody Long amount) {
		String msg = priceService.updatePrice(id, amount);
		if(msg == null) {
			throw new ProductNotFoundException("Product "+id+" is not found.");
		}
		ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.OK);
		return response;
	}
	@DeleteMapping("/products/{id}/price")
	public ResponseEntity<String> deletePrice(@PathVariable Integer id) {
		Price price = priceService.deletePrice(id);
		if(price == null) {
			throw new ProductNotFoundException("Product "+id+" is not found.");
		}
		return null;
	}
}
