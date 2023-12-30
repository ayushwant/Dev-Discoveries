package com.example.demo.model;

import java.util.Arrays;
import java.util.List;

public class PhoneBrand
{
    Integer id;
    String name;

    public PhoneBrand(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    List<PhoneBrand> phoneBrandList = Arrays.asList(
            new PhoneBrand(1, "Nokia"),
            new PhoneBrand(2, "Apple"),
            new PhoneBrand(3, "Samsung")
    );

//    public PhoneBrand getBrandById(Integer id){
//        return phoneBrandList.stream().filter( brand -> brand.id.equals(id) );
//    }
}
