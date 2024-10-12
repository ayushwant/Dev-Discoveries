package com.example.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach {

    // No args constructors are auto called by Spring. Even if we don't specify it explicitly.
    // We are specifying it here coz we want something to happen when bean constructor is called.
    public TennisCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }
}