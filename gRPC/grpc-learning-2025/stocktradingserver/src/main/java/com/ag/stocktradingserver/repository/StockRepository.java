package com.ag.stocktradingserver.repository;

import com.ag.stocktradingserver.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Stock findBySymbol(String symbol);
}
