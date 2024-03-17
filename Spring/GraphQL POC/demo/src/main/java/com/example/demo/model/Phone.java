package com.example.demo.model;

import java.util.Arrays;
import java.util.List;

public class Phone
{
    Integer id;
    public Integer brandId;
    Integer price;
    String name, model;

    public Phone(Integer id, Integer brandId, Integer price, String name, String model) {
        System.out.printf("Phone constructor: %d %d %d %s %s\n", id, brandId, price, name, model);
        this.id = id;
        this.brandId = brandId;
        this.price = price;
        this.name = name;
        this.model = model;
    }

    public static List<Phone> phoneList = Arrays.asList(
            new Phone(1, 1, 100, "3310", "Basic"),
            new Phone(2, 1, 100, "10X", "Smart"),
            new Phone(3, 2, 1000, "iPhone 7", "Smart"),
            new Phone(4, 2, 10000, "iPhone 15", "Super smart"),
            new Phone(5, 3, 2000, "Samsung A23", "Smart"),
            new Phone(6, 3, 5000, "Samsung S23", "Super smart")
    );
}
