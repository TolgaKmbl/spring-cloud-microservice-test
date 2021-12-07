package com.tolgakmbl.microservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakController {

	private Logger logger = LoggerFactory.getLogger(CircuitBreakController.class);
	
	
	@GetMapping("/sample-api")
//	@Retry(name="default", fallbackMethod="defaultResponse")
//	@CircuitBreaker(name="default", fallbackMethod="defaultResponse")
//	@RateLimiter(name="default")
	@Bulkhead(name="default")
	public String sampleApi() {		
		logger.info("Sample API call received");		
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/testing-failure", 
				String.class);		
		return forEntity.getBody();
	}
	
	private String defaultResponse(Exception e) {
		return "This is a default response";
	}
	
}
