package com.meru.eurekaserver.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class EurekaServerController {
	
	public String test() {
		return "Eureka Serveris up";
	}

}
