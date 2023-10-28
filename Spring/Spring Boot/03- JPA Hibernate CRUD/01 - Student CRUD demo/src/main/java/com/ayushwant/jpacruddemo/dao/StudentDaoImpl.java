package com.ayushwant.jpacruddemo.dao;

import com.ayushwant.jpacruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository  // specialized annotation for DAO implementations
public class StudentDaoImpl implements StudentDAO{

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public StudentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional // to handle the transactions to the DB
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }
}
