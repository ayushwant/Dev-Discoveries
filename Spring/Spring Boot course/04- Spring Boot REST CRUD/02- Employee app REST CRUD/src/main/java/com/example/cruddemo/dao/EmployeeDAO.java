package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();

    Employee findByID(int ID);

    Employee save(Employee employee);

    void deleteByID(int ID);
}
