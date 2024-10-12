package com.ayushwant.jpacruddemo.dao;

import com.ayushwant.jpacruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository  // specialized annotation for DAO implementations
public class StudentDaoImpl implements StudentDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public StudentDaoImpl(EntityManager entityManager) {
        System.out.println("In StudentDaoImpl constructor");
        this.entityManager = entityManager;
    }

    @Override
    @Transactional // to handle the transactions to the DB
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findByID(Integer id) {
        return entityManager.find(Student.class, id);
    }

    /*
    JPQL is based on **entity name** and **entity fields.**
    This means, that even if our table name in Database is  some <XYZ>, and our entity class name is Student, we’ll write query like we’re querying Student.
    We’ll use the name of Student class and all its fields, rather than the actual table name and its fields in the DB.     */
    @Override
    public List<Student> findAll()
    {
        System.out.println("In method findAll of StudentDaoImpl");
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student order by lastName desc", Student.class);
        // Student = Name of JPA Entity, the class name
        // It is NOT the name of the actual database table
        System.out.println("before getResultList");

        List<Student> students = theQuery.getResultList();

        System.out.println("after getResultList");

        return students;
    }

    @Override
    public List<Student> findByLastName() {
        TypedQuery<Student> theQuery = entityManager.createQuery(
                "FROM Student WHERE lastName=:theData", Student.class);
        // JPQL Named Parameters are prefixed with a colon :
        // Think of this as a placeholder that is filled in later

        theQuery.setParameter("theData", "Doe"); // we fill in the param here

        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Student theStudent = entityManager.find(Student.class, id);
        entityManager.remove(theStudent);
    }
}
