package com.example.springcoredemo.config;

import com.example.springcoredemo.common.Coach;
import com.example.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
The @Configuration annotation is used to indicate that a Java class defines a configuration for the Spring container.
A @Configuration class is a way to define and configure Spring beans,
often used in conjunction with the @Bean annotation.
 */

@Configuration
public class SportsConfig {

    @Bean
//    @Bean("aquatic")  // we can also define custom ID of our bean
    public Coach swimCoach() {
        return new SwimCoach();
    }
}
