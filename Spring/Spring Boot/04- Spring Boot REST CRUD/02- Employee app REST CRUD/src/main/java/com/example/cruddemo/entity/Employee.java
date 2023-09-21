package com.example.cruddemo.entity;

import jakarta.persistence.*;
/*
@Entity is a Java annotation used in the Java Persistence API (JPA) to denote a class as a persistent entity.
In the context of JPA, an "entity" represents a table in a relational database.
Each instance of an entity corresponds to a row in that table
 */

// Mark this class as entity and point to the Employee table in database
@Entity
@Table(name="employee")
public class Employee
{
    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Column(name="first_name")
    String firstName;

    @Column(name="last_name")
    String lastName;

    @Column(name="email")
    String email;

    // define constructors
    public Employee(){}

    public Employee(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // define getters and setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    // define toString

    @Override
    public String toString() {
        return "Employee{" +
                "ID=" + ID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
