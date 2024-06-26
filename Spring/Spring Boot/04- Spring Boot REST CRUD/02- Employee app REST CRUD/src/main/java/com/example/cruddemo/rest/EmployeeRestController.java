package com.example.cruddemo.rest;

import com.example.cruddemo.dao.EmployeeDAO;
import com.example.cruddemo.entity.Employee;
import com.example.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// This class is fired whenever "api" endpoint hits
// It calls the Service to get the data
@RestController
@RequestMapping("api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // expose "/employees" and return the list of employees
    @GetMapping("employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    // Method to get employee by ID
    @GetMapping("employees/{employeeID}")
    public Employee getEmployee(@PathVariable int employeeID){
        Employee emp = employeeService.findByID(employeeID);

        if(emp==null){
            throw new RuntimeException("Employee with ID = " + employeeID + " not found!");
        }

        return emp;
    }

    @PostMapping("employees")
    public Employee addEmployee(@RequestBody Employee employee)
    {
        //default (bahut samajh ni aaya)
        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update
        employee.setID(0);

        Employee emp = employeeService.save(employee);

        return emp;
    }

    // update employee
    @PutMapping("employees")
    public Employee updateEmployee(@RequestBody Employee employee)
    {
        Employee emp = employeeService.save((employee));

        return emp;
    }

    //delete employee
    @DeleteMapping("employees/{employeeID}")
    public Employee deleteEmployee(@PathVariable int employeeID)
    {
        Employee emp = employeeService.findByID(employeeID);

        if(emp==null){
            throw new RuntimeException("Employee with ID = " + employeeID + " not found!");
        }

        employeeService.deleteByID(employeeID);

        return emp;
    }
}
