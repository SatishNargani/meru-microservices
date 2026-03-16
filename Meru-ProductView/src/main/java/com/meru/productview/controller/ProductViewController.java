package com.meru.productview.controller;

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

import com.meru.productview.entity.ProductView;
import com.meru.productview.exception.ProductViewNotFound;
import com.meru.productview.service.ProductViewService;
@RestController
@RefreshScope
public class ProductViewController {
	@Value("${filename}")
	private String filename;
	@Value("${server.port}")
	String serverport;
	
	@Autowired
	ProductViewService service;
	
	@GetMapping("/test")
	public String test() {
		return filename + " Service is working on port : "+serverport;	}
	
	@GetMapping("/testribbon")
	public String testribbon() {
		return service.testRibbon();
	}
	
	@GetMapping("/productview")
	public Iterable<ProductView> getProductView() {
		return service.getProductAllView();
	}
	
	@GetMapping("/products/{id}/productview")
	public ProductView getProductView(@PathVariable Integer id) {
		ProductView productView = service.getProductView(id);
		if(productView==null) {
			throw new ProductViewNotFound("Product View for product id"+id+"not found.");
		}
		return productView;
	}
	
	@GetMapping("/products/{id}/applypromotion")
	public String getProductPriceyApplyPromotion(@PathVariable Integer id) {
		String price = service.getProductPriceByApplyPromotion(id);
		if(price==null) {
			throw new ProductViewNotFound("Product View for product id"+id+"not found.");
		}
		return price;
	}
	
	@PostMapping("/products/{id}/productview")
	public ResponseEntity<String> addProductView(@PathVariable Integer id, @RequestBody ProductView productView) {
		ProductView savedProductView = service.addProductView(new ProductView(productView.getProductId(), productView.getProductName(), productView.getProductDescription(), 0, (long)0));
		ResponseEntity<String> response = new ResponseEntity<String>(savedProductView.toString(), HttpStatus.OK);
		return response;
	}
	
	@PutMapping("/products/{id}/productview/updatedescription")
	public ResponseEntity<String> updateProductViewDescription(@PathVariable Integer id, @RequestBody ProductView productView ){
		ProductView savedProductView = service.updateProductDesc(id, productView.getProductDescription());
		if(savedProductView == null) {
			throw new ProductViewNotFound("Product View for product id"+id+"not found.");
		}
		ResponseEntity<String> response = new ResponseEntity<String>(savedProductView.toString(), HttpStatus.OK);
		return response;
	}
	
	@PutMapping("/products/{id}/productview/updateprice")
	public ResponseEntity<String> updateProductViewPrice(@PathVariable Integer id, @RequestBody ProductView productView ){
		ProductView savedProductView = service.updateProductPrice(id, productView.getPrice());
		if(savedProductView == null) {
			throw new ProductViewNotFound("Product View for product id"+id+"not found.");
		}
		ResponseEntity<String> response = new ResponseEntity<String>(savedProductView.toString(), HttpStatus.OK);
		return response;
	}
	
	@PutMapping("/products/{id}/productview/updateinventory")
	public ResponseEntity<String> updateProductDescriptionViewInventory(@PathVariable Integer id, @RequestBody ProductView productView ){
		ProductView savedProductView = service.updateProductInventory(id, productView.getInventory());
		if(savedProductView == null) {
			throw new ProductViewNotFound("Product View for product id"+id+"not found.");
		}
		ResponseEntity<String> response = new ResponseEntity<String>(savedProductView.toString(), HttpStatus.OK);
		return response;
	}
	
	@DeleteMapping("/products/{id}/productview")
	public ResponseEntity<String> deleteProductView(@PathVariable Integer id){
		ProductView deletedProductView = service.deleteByProductId(id);
		if(deletedProductView == null) {
			throw new ProductViewNotFound("Product View for product id"+id+"not found.");
		}
		ResponseEntity<String> response = new ResponseEntity<String>(deletedProductView.toString(), HttpStatus.OK);
		return response;
	}
}
