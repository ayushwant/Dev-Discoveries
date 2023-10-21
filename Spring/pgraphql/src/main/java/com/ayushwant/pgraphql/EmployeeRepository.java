package com.ayushwant.pgraphql;

import com.ayushwant.pgraphql.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}