package com.meru.product.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.meru.product.entity.Product;

@SpringBootTest
class ProductServiceTest {
	
	@Autowired
	ProductService productService;

	@Test
	void testGetAllProduct() {
		System.out.println("productService.countProduct()  ==  "+productService.countProduct()); 
		Iterable<Product> products = productService.getAllProduct();
		for(Product product : products) {
			System.out.println(product); 
		}
		assertEquals(5, productService.countProduct()); 
	}

	@Test
	void testIsExists() {
		assertEquals(true, productService.isExists(1));
		assertEquals(true, productService.isExists(2));
		assertEquals(false, productService.isExists(11));
		assertEquals(false, productService.isExists(12));
	}

	@Test
	void testGetProduct() {
		Product product = productService.getProduct(1);
		assertEquals("Note 7", product.getProductName());
		assertEquals("Note 7 Smart Phone", product.getProductDescription());
	}

	@Test
	void testAddProduct() {
		System.out.println("productService.countProduct()  ==  "+productService.countProduct()); 
		productService.addProduct(new Product("Note 1","Note 1 Smart Phone"));
		System.out.println("productService.countProduct()  ==  "+productService.countProduct()); 
	}

	@Test
	void testUpdateProduct() {
//		String message = productService.updateProduct(1, new Product("Note - 7", "Note - 7 Smart Phone"));
//		assertEquals("Note - 7", product.getProductName());
//		assertEquals("Note - 7 Smart Phone", product.getProductDescription());
//		product = productService.updateProduct(1, new Product("Note 7", "Note 7 Smart Phone"));
//		assertEquals("Note 7", product.getProductName());
//		assertEquals("Note 7 Smart Phone", product.getProductDescription());
	}

	@Test
	void testDeleteProduct() {
		assertEquals(true, productService.isExists(14));
		productService.deleteProduct(14);
		assertEquals(false, productService.isExists(14));

	}

}
