package com.meru.inventory.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.meru.inventory.entity.Inventory;
import com.meru.inventory.repository.InventoryRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class InventoryService {
	@Autowired
	private EurekaClient eurekaClient;
	@Autowired
	private RestTemplateBuilder restTemplateBuilder;
	@Autowired
	InventoryRepository inventoryRepository;
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	@LoadBalanced
	RestTemplate restTemplate(){
		return restTemplateBuilder.build();
	}
	
	public Inventory getByProductId(Integer productId) {
		Optional<Inventory> inventry = inventoryRepository.findByProductId(productId);
		if(inventry.isPresent()) {
			return inventry.get();
		}
		return null;
	}

	public long countInventry() {
		return inventoryRepository.count();
	}
	
	public Inventory createInventory(Inventory inventory) {
		return inventoryRepository.save(inventory);
	}
	
	@Transactional
	@HystrixCommand(fallbackMethod = "addInventory_defaultMessge")
	public String addInventory(Integer id, int inv) {
		Optional<Inventory> inventorys = inventoryRepository.findByProductId(id);
		if(inventorys.isPresent()) {
			Inventory inventory = inventorys.get();
			inventory.setInventory(inventory.getInventory()+inv);
			inventory = inventoryRepository.save(inventory);
			
			//update product view
			restTemplate.put("http://meru-zuul-gateway/api/productview/products/"+id+"/productview/updateinventory", inventory);

			
			return "Inventory added successfully. Updated inventry is "+ inventory.getInventory();
		}
		return null;
	}
	public String addInventory_defaultMessge(Integer id, int inv) {
		return "Some of the services might be down so operation can not be performed. Please try after some time.";
	}
	
	@Transactional
	@HystrixCommand(fallbackMethod = "deductInventory_defaultMessge")
	public String deductInventory(Integer id, int inv) {
		Optional<Inventory> inventorys = inventoryRepository.findByProductId(id);
		if(inventorys.isPresent()) {
			Inventory inventory = inventorys.get();
			if(inventory.getInventory()>=inv) {
				inventory.setInventory(inventory.getInventory()-inv);
				inventory = inventoryRepository.save(inventory);
				
				//update product view
				restTemplate.put("http://meru-zuul-gateway/api/productview/products/"+id+"/productview/updateinventory", inventory);
				
				return "Inventory deducted successfully. Updated inventry is "+ inventory.getInventory();
			} else {
				return "Not sufficent inventory to be deducted.";
			}
			
		}
		return null;
	}
	public String deductInventory_defaultMessge(Integer id, int inv) {
		return "Some of the services might be down so operation can not be performed. Please try after some time.";
	}

	public Inventory deleteByProductId(Integer productId) {
		Optional<Inventory> inventorys = inventoryRepository.findByProductId(productId);
		if(inventorys.isPresent()) {
			Inventory inventory = inventorys.get();
			inventoryRepository.delete(inventory); 
			return inventory;
		}
		return null;
	}

}
