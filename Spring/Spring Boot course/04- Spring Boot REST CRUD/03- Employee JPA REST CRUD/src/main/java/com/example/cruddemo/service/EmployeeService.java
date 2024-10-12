package com.example.cruddemo.service;

import com.example.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findByID(int ID);

    Employee save(Employee employee);

    void deleteByID(int ID);
}
