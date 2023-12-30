//package com.example.demo.controller;
//
//import com.example.demo.model.QEmployee;
//import com.example.demo.repository.EmployeeRepository;
//import com.example.demo.model.Employee;
//import com.querydsl.core.Tuple;
//import com.querydsl.jpa.impl.JPAQuery;
//import org.hibernate.Criteria;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.graphql.data.method.annotation.QueryMapping;
//import org.springframework.stereotype.Controller;
//
//import javax.persistence.EntityManager;
//import java.util.ArrayList;
//import java.util.List;
//
//// generate comments for this class
//@Controller
//public class EmployeeGraphQLController
//{
//    private EmployeeRepository employeeRepository;
//
//    private EntityManager entityManager;
//
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    @Autowired
//    public EmployeeGraphQLController(EmployeeRepository employeeRepository, EntityManager entityManager) {
//        this.employeeRepository = employeeRepository;
//        this.entityManager = entityManager;
//    }
//
//    private QEmployee qEmployee = QEmployee.employee;
//
//    @QueryMapping
//    public List<Employee> employees()
//    {
////        System.out.println(employeeRepository.findAll());
////        return employeeRepository.findAll();
//
//        // using query dsl
//        JPAQuery<Employee> jpaQuery = new JPAQuery<>(entityManager);
//
//        // Method 1 : using tuple
//        List<Tuple> tuples = jpaQuery
//                .select(qEmployee.id, qEmployee.first_name)  //dynamic
//                .from(qEmployee)
//                .fetch();
//
//        List<Employee> employees = new ArrayList<>();
//
//
//        for(Tuple eachTuple: tuples){
//            Employee employee = new Employee();
//            employee.setId(eachTuple.get(qEmployee.id));
//            employee.setFirst_name(eachTuple.get(qEmployee.first_name));
//
//            employees.add(employee);
//        }
//
//
//        ////// printing list of tables
////        Session session = sessionFactory.openSession();
//
////        List<Object> list = session.createSQLQuery("select table_name from user_tables").list();
////
////        for(Object l : list){
////            System.out.println("L : " +l.toString());
////        }
//
//
//        return employees;
//    }
//}
