package com.example.demo.controller;

import com.example.demo.repository.EmployeeRepository;
import com.example.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeGraphQLController
{
    @Autowired
    private EmployeeRepository employeeRepository;

    @QueryMapping
    public Iterable<Employee> employees()
    {
        System.out.println(employeeRepository.findAll());
        return employeeRepository.findAll();
    }
}
