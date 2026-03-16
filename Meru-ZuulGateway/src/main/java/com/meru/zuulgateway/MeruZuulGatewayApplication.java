package com.meru.zuulgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import com.meru.zuulgateway.filter.ErrorFilter;
import com.meru.zuulgateway.filter.PostFilter;
import com.meru.zuulgateway.filter.PreFilter;
import com.meru.zuulgateway.filter.RouterFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class MeruZuulGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeruZuulGatewayApplication.class, args);
	}
	public PreFilter preFilter() {
		return new PreFilter();
	}
	public PostFilter postFilter() {
		return new PostFilter();
	}
	public RouterFilter routerFilter() {
		return new RouterFilter();
	}
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}
}
