package com.example.demo.model;

import java.util.Arrays;
import java.util.List;

public class PhoneBrand
{
    public Integer id;
    String name;

    public PhoneBrand(Integer id, String name) {
        System.out.printf("PhoneBrand constructor: %d %s\n", id, name);
        this.id = id;
        this.name = name;
    }

    public static List<PhoneBrand> phoneBrandList = Arrays.asList(
            new PhoneBrand(1, "Nokia"),
            new PhoneBrand(2, "Apple"),
            new PhoneBrand(3, "Samsung")
    );

//    public PhoneBrand getBrandById(Integer id){
//        return phoneBrandList.stream().filter( brand -> brand.id.equals(id) );
//    }
}
