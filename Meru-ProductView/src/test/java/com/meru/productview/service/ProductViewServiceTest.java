package com.meru.productview.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.meru.productview.entity.ProductView;

@SpringBootTest
class ProductViewServiceTest {
	
	@Autowired
	ProductViewService service;
	

	@Test
	void testGetProductView() {
		ProductView productView = service.getProductView(2);
		assertEquals("Note 6", productView.getProductName());
		assertEquals("Note 6 Smartphone", productView.getProductDescription());
	}

	@Test
	void testAddProductView() {
		assertEquals(0, service.count());
		service.addProductView(new ProductView(1, "Note 7", "Note 7 Smartphone", 0, (long)0));
		service.addProductView(new ProductView(2, "Note 6", "Note 6 Smartphone", 0, (long)0));
		service.addProductView(new ProductView(3, "Note 5", "Note 5 Smartphone", 0, (long)0));
		service.addProductView(new ProductView(4, "Note 4", "Note 4 Smartphone", 0, (long)0));
		service.addProductView(new ProductView(5, "Note 3", "Note 3 Smartphone", 0, (long)0));
		assertEquals(5, service.count());
	}

	@Test
	void testUpdateProductDesc() {
		ProductView productView = service.updateProductDesc(2, "Note - 6 Smartphone");
		assertEquals("Note 6", productView.getProductName());
		assertEquals("0", productView.getPrice());
		assertEquals("0", productView.getInventory());
		assertEquals("Note - 6 Smartphone", productView.getProductDescription());
	}

	@Test
	void testUpdateProductPrice() {
		service.updateProductPrice(2, (long) 500);
		ProductView productView = service.getProductView(2);
		assertEquals("Note 6", productView.getProductName());
		assertEquals(500, productView.getPrice());
		assertEquals(25, productView.getInventory());
		assertEquals("Note - 6 Smartphone", productView.getProductDescription());
	}

	@Test
	void testUpdateProductInventory() {
		service.updateProductInventory(2, 40);
		ProductView productView = service.getProductView(2);
		assertEquals("Note 6", productView.getProductName());
		assertEquals(500, productView.getPrice());
		assertEquals(40, productView.getInventory());
		assertEquals("Note - 6 Smartphone", productView.getProductDescription());
	}

	@Test
	void testDeleteByProductId() {
		assertEquals(5, service.count());
		service.deleteByProductId(1);
		assertEquals(4, service.count());
	}

}
