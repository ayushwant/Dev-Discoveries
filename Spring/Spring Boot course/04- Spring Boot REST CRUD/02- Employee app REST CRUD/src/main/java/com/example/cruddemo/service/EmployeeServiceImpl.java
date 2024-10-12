package com.example.cruddemo.service;

import com.example.cruddemo.dao.EmployeeDAO;
import com.example.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// It is called by the Controller to get the data
// It calls the EmployeeDAO (and any other DAOs if necessary) to get the data

// Just delegate all the calls to the DAO
@Service
public class EmployeeServiceImpl implements EmployeeService
{
    // inject the DAO
    EmployeeDAO employeeDAO;

    // use constructor injection
    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Transactional
    @Override
    public Employee findByID(int ID) {
        return employeeDAO.findByID(ID);
    }

    @Transactional
    @Override
    public Employee save(Employee employee) {
        return employeeDAO.save(employee);
    }

    @Transactional
    @Override
    public void deleteByID(int ID) {
        employeeDAO.deleteByID(ID);
    }
}
