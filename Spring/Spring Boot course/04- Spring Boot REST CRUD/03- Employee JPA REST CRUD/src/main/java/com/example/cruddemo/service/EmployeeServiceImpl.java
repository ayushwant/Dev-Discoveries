package com.example.cruddemo.service;

import com.example.cruddemo.repository.EmployeeRepository;
import com.example.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// It is called by the Controller to get the data
// It calls the EmployeeDAO (and any other DAOs if necessary) to get the data

// Just delegate all the calls to the Repository
@Service
public class EmployeeServiceImpl implements EmployeeService
{
    // inject the Repository
    EmployeeRepository employeeRepository;

    // use constructor injection
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // We'll use the free methods we get with JpaRepository by default
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    // We don't need @Transactional annotation. JpaRepository handles it for us
    @Override
    public Employee findByID(int ID) {
        Optional<Employee> result = employeeRepository.findById(ID);

        Employee employee = null;

        if(result.isPresent())
            employee = result.get();
        else{
            throw new RuntimeException("Didn't find Employee with id = " + ID);
        }

        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteByID(int ID) {
        employeeRepository.deleteById(ID);
    }
}
