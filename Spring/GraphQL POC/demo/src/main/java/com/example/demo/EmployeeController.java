package com.example.demo;

import com.example.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping(path="/demo")
public class EmployeeController
{
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Employee> getEmployees(){
        System.out.println(employeeRepository.findAll());
        return employeeRepository.findAll();
    }
}
