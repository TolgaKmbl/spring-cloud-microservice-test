package com.tolgakmbl.microservices.currencyexchangeservice.service;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.tolgakmbl.microservices.currencyexchangeservice.exception.CurrencyNotFoundException;
import com.tolgakmbl.microservices.currencyexchangeservice.model.CurrencyExchange;
import com.tolgakmbl.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurrencyExchangeService {

	private final CurrencyExchangeRepository currencyExchangeRepository;
	private final Environment environment;
	
	public CurrencyExchange getExchangeValue(String from, String to) {		
		
		CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
		if(currencyExchange == null) {
			throw new CurrencyNotFoundException("Currency is not found from " + from + " to " + to);
		}
		currencyExchange.setEnvironment(getEnvironmentProperties());
				
		return currencyExchange;
	}
	
	private String getEnvironmentProperties() {
		return environment.getProperty("local.server.port");
	}

}
