package com.meru.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
@EnableCircuitBreaker
public class MeruInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeruInventoryApplication.class, args);
	}

}
