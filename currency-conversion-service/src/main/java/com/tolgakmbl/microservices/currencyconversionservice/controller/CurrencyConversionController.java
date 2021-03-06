package com.tolgakmbl.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.tolgakmbl.microservices.currencyconversionservice.CurrencyExchangeProxy;
import com.tolgakmbl.microservices.currencyconversionservice.model.CurrencyConversion;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CurrencyConversionController {

	private final CurrencyExchangeProxy proxy;
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(@PathVariable("from") String from, 
			@PathVariable("to") String to, 
			@PathVariable("quantity") BigDecimal quantity) {
		
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
				CurrencyConversion.class,
				uriVariables
				);
		
		CurrencyConversion curencyConversion = responseEntity.getBody();
		
		return new CurrencyConversion(
				curencyConversion.getId(), 
				from, 
				to,
				curencyConversion.getConversionMultiple(), 
				quantity, 
				quantity.multiply(curencyConversion.getConversionMultiple()),
				curencyConversion.getEnvironment()
				);
		
	}
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable("from") String from, 
			@PathVariable("to") String to, 
			@PathVariable("quantity") BigDecimal quantity) {
		
		CurrencyConversion curencyConversion = proxy.getExchangeValue(from, to);
		
		return new CurrencyConversion(
				curencyConversion.getId(), 
				from, 
				to,
				curencyConversion.getConversionMultiple(), 
				quantity, 
				quantity.multiply(curencyConversion.getConversionMultiple()),
				curencyConversion.getEnvironment()
				);
		
	}
	
}
