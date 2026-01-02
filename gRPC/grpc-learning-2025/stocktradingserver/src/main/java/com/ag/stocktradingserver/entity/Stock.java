package com.ag.stocktradingserver.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "symbol", unique = true, nullable = false)
    private String symbol;

    private double price;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
}