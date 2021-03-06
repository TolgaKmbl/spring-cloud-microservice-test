package com.tolgakmbl.microservices.currencyexchangeservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tolgakmbl.microservices.currencyexchangeservice.model.CurrencyExchange;
import com.tolgakmbl.microservices.currencyexchangeservice.service.CurrencyExchangeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CurrencyExchangeController {
	
	private final CurrencyExchangeService currencyExchangeService;	

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange getExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to) {
		
		return this.currencyExchangeService.getExchangeValue(from, to);
		
	}

}
