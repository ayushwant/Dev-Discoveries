package com.luv2code.cruddemo.entity.generated_using_intellij;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//@Entity
@Table(name = "student", schema = "hb-05-many-to-many")
public class Student {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "first_name", length = 45)
    private String firstName;

    @Column(name = "last_name", length = 45)
    private String lastName;

    @Column(name = "email", length = 45)
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

}