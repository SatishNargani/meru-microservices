package com.meru.product.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.meru.product.entity.Product;
import com.meru.product.repository.ProductRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ProductService {
	@Autowired
	private EurekaClient eurekaClient;
	@Autowired
	private RestTemplateBuilder restTemplateBuilder;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	@LoadBalanced
	RestTemplate restTemplate(){
		return restTemplateBuilder.build();
	}
	
	public String getPriceTestMsg() {
		String url = "http://meru-zuul-gateway/api/price/test";
		return restTemplate.getForObject(url, String.class);
	}
	
	public boolean isExists(Integer id) {
		return productRepository.existsById(id);
	}
	
	public Iterable<Product> getAllProduct(){
		return productRepository.findAll();
	}
	
	public Product getProduct(Integer id){
		Optional<Product> product = productRepository.findById(id);
		if(product.isPresent()) {
			return product.get();
		}
		return null;
	}
	
	public String testRibbon() {
		String msg= null;
		try {
			//String baseurl = eurekaClient.getNextServerFromEureka("http://eureka-service-app/msg""MERU-PRODUCTVIEW", false).getHomePageUrl() + "testribbon";
			//System.out.println("baseurl  ==  " + baseurl);
			msg = this.restTemplate.getForObject("http://MERU-PRODUCTVIEW/testribbon", String.class, String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	@Transactional
	public Product addProduct(Product product) {
		//RestTemplate restTemplate;
		InstanceInfo instanceInfo;
		String baseurl;
		ResponseEntity<String> response;
		//Create Product
		Product savedProduct = productRepository.save(product);
		//Create Inventory
		response = restTemplate.postForEntity("http://meru-zuul-gateway/api/inventory/products/"+savedProduct.getProductId()+"/inventory", savedProduct, String.class);
		//Create Price
		response = restTemplate.postForEntity("http://meru-zuul-gateway/api/price/products/"+savedProduct.getProductId()+"/price", savedProduct, String.class);
		//Create Product View 
		response = restTemplate.postForEntity("http://meru-zuul-gateway/api/productview/products/"+savedProduct.getProductId()+"/productview", savedProduct, String.class);
		return savedProduct;
	}
	
	
	@Transactional
	@HystrixCommand(fallbackMethod = "updateProduct_defaultMessge")
	public String updateProduct(Integer id, Product product) {
		
		try {
			//update product
			Optional<Product> productOption = productRepository.findById(id);
			if (productOption.isPresent()) {
				Product updateProduct = productOption.get();
				//updateProduct.setProductName(product.getProductName());
				updateProduct.setProductDescription(product.getProductDescription());
				updateProduct = productRepository.save(updateProduct);
				restTemplate.put("http://meru-zuul-gateway/api/productview/products/"+id+"/productview/updatedescription", updateProduct);
				return "Product updated successfully " + updateProduct.toString();
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String updateProduct_defaultMessge(Integer id, Product product) {
		return "Some of the services might be down so operation can not be performed. Please try after some time.";
	}
	
	@Transactional
	public Product deleteProduct(Integer id) {
		
		//delete Product
		Optional<Product> productOption = productRepository.findById(id);
		if(productOption.isPresent()) {
			Product product = productOption.get();
			productRepository.delete(product);
			//delete Inventory
			restTemplate.delete("http://meru-zuul-gateway/api/inventory/products/"+id+"/inventory");
			//delete Price
			restTemplate.delete("http://meru-zuul-gateway/api/price/products/"+id+"/price");
			//delete Product View
			restTemplate.delete("http://meru-zuul-gateway/api/productview/products/"+id+"/productview");
			return product;
		}
		return null;
	}
	
	public long countProduct() {
		return productRepository.count();
	}
}
