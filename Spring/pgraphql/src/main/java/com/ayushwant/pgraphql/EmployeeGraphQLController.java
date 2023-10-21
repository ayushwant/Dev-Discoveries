package com.ayushwant.pgraphql;

import com.ayushwant.pgraphql.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeGraphQLController
{
    @Autowired
    private EmployeeRepository employeeRepository;

    @QueryMapping
    public Iterable<Employee> employees(){
        System.out.println(employeeRepository.findAll());
        return employeeRepository.findAll();
    }
}
