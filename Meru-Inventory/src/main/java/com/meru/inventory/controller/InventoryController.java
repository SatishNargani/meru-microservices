package com.meru.inventory.controller;

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

import com.meru.inventory.entity.Inventory;
import com.meru.inventory.exception.InventoryNotFoundException;
import com.meru.inventory.service.InventoryService;
@RestController
@RefreshScope
public class InventoryController {
	@Value("${filename}")
	private String filename;
	@Value("${server.port}")
	String serverport;
	
	@Autowired
	InventoryService inventoryService;
	
	@GetMapping("/test")
	public String test() {
		return filename + " Service is working on port : "+serverport;
	}
	
	@GetMapping("/products/{id}/inventory")
	public Inventory getInventory(@PathVariable Integer id) {
		Inventory inventory = inventoryService.getByProductId(id);
		if(inventory!=null) {
			return inventory;
		}
		throw new InventoryNotFoundException("Inventory for Product Id "+id+" not found.");
	}
	@PostMapping("/products/{id}/inventory")
	public ResponseEntity<String> createInventory(@PathVariable Integer id) {
		Inventory createdInventory = inventoryService.createInventory(new Inventory(id, 0));
		ResponseEntity<String> response = new ResponseEntity<String>(createdInventory.toString(), HttpStatus.OK);
		return response;
	}
	@PutMapping("/products/{id}/addinventory")
	public ResponseEntity<String> addInventory(@PathVariable Integer id, @RequestBody Integer inv) {
		String msg = inventoryService.addInventory(id, inv);
		if(msg == null) {
			throw new InventoryNotFoundException("Inventory for Product Id "+id+" not found.");
		}
		ResponseEntity<String> response =  new ResponseEntity<String>(msg, HttpStatus.OK);
		return response;
	}
	@PutMapping("/products/{id}/deductinventory")
	public ResponseEntity<String> deductInventory(@PathVariable Integer id, @RequestBody Integer inv) {
		String msg = inventoryService.deductInventory(id, inv);
		if(msg == null) {
			throw new InventoryNotFoundException("Inventory for Product Id "+id+" not found.");
		}
		ResponseEntity<String> response =  new ResponseEntity<String>(msg, HttpStatus.OK);
		return response;
	}
	@DeleteMapping("/products/{id}/inventory")
	public ResponseEntity<String> deleteInventory(@PathVariable Integer id) {
		Inventory inventory = inventoryService.deleteByProductId(id);
		if(inventory == null) {
			throw new InventoryNotFoundException("Inventory for Product Id "+id+" not found.");
		}
		ResponseEntity<String> response =  new ResponseEntity<String>("Inventory "+inventory.toString()+"is deleted successfully.", HttpStatus.OK);
		return response;
	}
}
