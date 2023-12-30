package com.example.demo.controller;

import com.example.demo.repository.EmployeeRepository;
import com.example.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/demo")
public class EmployeeRestController
{
    @Autowired
    private EmployeeRepository employeeRepository;

    // we need to add annotation @ResponseBody to convert the return value to JSON when not using
    // @RestController annotation
    @GetMapping(path="/employees")
    public @ResponseBody Iterable<Employee> getEmployees(){
        System.out.println(employeeRepository.findAll());
        return employeeRepository.findAll();
    }

    @GetMapping(path="/employee")
    public Employee getEmployeeById(Integer id){
        return employeeRepository.findById(id).get();
    }

    @PostMapping(path="/employee")
    public Employee saveEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }
}
