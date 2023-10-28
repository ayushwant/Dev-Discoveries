package com.ayushwant.jpacruddemo.dao;


import com.ayushwant.jpacruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {
    void save(Student theStudent);

    Student findByID(Integer id);

    List<Student> findAll();

    List<Student> findByLastName();

}
