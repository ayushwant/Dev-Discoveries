package com.ag.stocktradingserver.service;

import com.ag.stocktradingserver.entity.Stock;
import com.ag.stocktradingserver.repository.StockRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StockInitializationService {

    private StockRepository stockRepository;

    // create a method to create initial stocks
    @PostConstruct
    public void init() {
        // Implementation
        Stock google = new Stock();
        google.setSymbol("GOOGL");
        google.setPrice(2800.00);
        google.setLastUpdated(java.time.LocalDateTime.now());

        stockRepository.save(google);

        System.out.println("Initial stock GOOGL created.");

        System.out.println(stockRepository.findAll());
    }
}
