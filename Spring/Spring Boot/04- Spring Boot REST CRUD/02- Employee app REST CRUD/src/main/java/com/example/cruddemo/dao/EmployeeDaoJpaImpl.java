package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

// It is called by the Service to fetch the data from database
// It makes calls to the Database and returns to the Service
@Repository
public class EmployeeDaoJpaImpl implements EmployeeDAO
{
    //define field for entity manager
    private EntityManager entityManager;

    //setup constructor injection
    @Autowired
    public EmployeeDaoJpaImpl(EntityManager entityManager){
        // entity manager is automatically created by Spring Boot, and we can directly inject it here
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        // create a query
        TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);

        // run the query and get the result
        List<Employee> employeeList = query.getResultList();

        // return the result
        return employeeList;
    }

    @Override
    public Employee findByID(int ID) {

        // Find and return the employee
        Employee emp = entityManager.find(Employee.class, ID);

        return emp;
    }

    @Override
    public Employee save(Employee employee) {
        Employee emp = entityManager.merge(employee);

        return emp; // return the new employee, as it has the updated id
    }

    @Override
    public void deleteByID(int ID) {

        // Find and delete employee
        Employee emp = entityManager.find(Employee.class, ID);
        entityManager.remove(emp);
    }
}
