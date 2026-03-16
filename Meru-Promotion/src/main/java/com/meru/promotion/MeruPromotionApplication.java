package com.meru.promotion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
@EnableDiscoveryClient
@EnableCircuitBreaker
public class MeruPromotionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeruPromotionApplication.class, args);
	}

}
