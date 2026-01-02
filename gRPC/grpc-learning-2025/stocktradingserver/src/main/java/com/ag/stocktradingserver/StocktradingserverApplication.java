package com.ag.stocktradingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StocktradingserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(StocktradingserverApplication.class, args);
		
		createInitialStocks();
	}

	public static void createInitialStocks() {

	}
}
