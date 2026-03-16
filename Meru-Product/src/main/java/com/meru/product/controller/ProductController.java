package com.meru.product.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meru.product.entity.Product;
import com.meru.product.exception.ProductNotFoundException;
import com.meru.product.service.ProductService;



@RestController
@RefreshScope
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Value("${filename}")
	private String filename;
	@Value("${server.port}")
	String serverport;
	
	@GetMapping("/test")
	public String test() {
		return filename + " Service is working on port : "+serverport;
	}
	
	@GetMapping("/testribbon")
	public String testRibbon() {
		return productService.getPriceTestMsg();
	}
	
	@GetMapping("/products")
	public Iterable<Product> getAllProduct(){
		Iterable<Product> products = productService.getAllProduct();
		if(!products.iterator().hasNext()) {
			throw new ProductNotFoundException("Not a single product exists.");
		}
		return products;
	}
	
	@GetMapping("/products/{id}")
	public Product getProduct(@PathVariable Integer id){
		Product product = productService.getProduct(id);
		if(product == null) {
			throw new ProductNotFoundException("Product "+id+" not found.");
		}
		return product;
	}
	
	@PostMapping("/products")
	public ResponseEntity<String> addProduct(@RequestBody Product product) {
		Product createdproduct = productService.addProduct(product);
		ResponseEntity<String> response = new ResponseEntity<String>(createdproduct.toString(), HttpStatus.OK);
		return response;
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<String> updateProduct(@PathVariable Integer id, @RequestBody Product product){
		String message = productService.updateProduct(id, product);
		if(message == null) {
			throw new ProductNotFoundException("Product "+id+" not found to be updated.");
		}
		ResponseEntity<String> response = new ResponseEntity<String>(message, HttpStatus.OK);
		return response;
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer id){
		Product deletedProduct = productService.deleteProduct(id);
		if(deletedProduct == null) {
			throw new ProductNotFoundException("Product "+id+" not found to be deleted.");
		}
		ResponseEntity<String> response = new ResponseEntity<String>(deletedProduct.toString(), HttpStatus.OK);
		return response;
	}
}
