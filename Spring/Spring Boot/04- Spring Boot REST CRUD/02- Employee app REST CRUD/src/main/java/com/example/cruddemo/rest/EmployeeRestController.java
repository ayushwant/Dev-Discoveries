package com.example.cruddemo.rest;

import com.example.cruddemo.dao.EmployeeDAO;
import com.example.cruddemo.entity.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class EmployeeRestController {

    private EmployeeDAO employeeDAO;
    // quick and dirty: inject Employee DAO (using constructor injection)
    // we should use a service layer in between the DAO and Client

    public EmployeeRestController(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    // expose "/employees" and return the list of employees
    @GetMapping("employees")
    public List<Employee> findAll(){
        return employeeDAO.findAll();
    }

}
