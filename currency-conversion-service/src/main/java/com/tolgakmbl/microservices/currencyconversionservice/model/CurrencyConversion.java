package com.tolgakmbl.microservices.currencyconversionservice.model;

import java.math.BigDecimal;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CurrencyConversion {

	private Long id;

	private String from;

	private String to;
	
	private BigDecimal conversionMultiple;
	
	private BigDecimal quantity;
	
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private BigDecimal totalCalculatedAmount;

	private String environment;	
	
	
	public BigDecimal getTotalCalculatedAmount() {
		return quantity.multiply(conversionMultiple);
	}

	 
}
