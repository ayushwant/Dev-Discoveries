package com.example.demo.controller;


import com.example.demo.model.Phone;
import com.example.demo.model.PhoneBrand;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PhoneController
{
    @QueryMapping
    public List<Phone> phones(){
        return Phone.phoneList;
    }

    @SchemaMapping
    public PhoneBrand brand(Phone phone){
        return PhoneBrand.phoneBrandList.stream()
                .filter( brand -> brand.id.equals(phone.brandId) ).findFirst().orElse(null);
    }
}
