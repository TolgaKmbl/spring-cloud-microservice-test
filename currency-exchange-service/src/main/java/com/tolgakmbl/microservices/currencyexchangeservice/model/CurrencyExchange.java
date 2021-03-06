package com.tolgakmbl.microservices.currencyexchangeservice.model;

import java.math.BigDecimal;

import javax.persistence.*;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class CurrencyExchange {

	@Id	
	private Long id;
	
	@Column(name="currency_from")
	private String from;
	
	@Column(name="currency_to")
	private String to;
	
	private BigDecimal conversionMultiple;
	
	private String environment;
	 
}
