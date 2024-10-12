package com.example.demo.rest;

public class StudentNotFoundException extends RuntimeException
{
    // create constructors for this exception using superclass constructors
    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentNotFoundException(Throwable cause) {
        super(cause);
    }
}
