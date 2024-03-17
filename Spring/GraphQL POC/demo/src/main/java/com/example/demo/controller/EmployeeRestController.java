package com.example.demo.controller;

import com.example.demo.repository.EmployeeRepository;
import com.example.demo.model.Employee;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping(path="/demo")
public class EmployeeRestController
{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    SessionFactory sessionFactory;

    // we need to add annotation @ResponseBody to convert the return value to JSON when not using
//     @RestController annotation
    @GetMapping(path="/employees")
    public @ResponseBody Flux<Employee> getEmployees(){
//        System.out.println(employeeRepository.findAll());
//        return employeeRepository.findAll();

        Session session = sessionFactory.openSession();

        ScrollableResults scrollableResults = session.createQuery("from Employee").scroll();

        AtomicInteger count = new AtomicInteger(0);

        return Flux.create(sink -> {
            while(scrollableResults.next()){
                if(count.getAndIncrement() % 10 == 0){
                    session.clear();
                    System.out.println("Clearing session");
                }
                sink.next((Employee) scrollableResults.get(0));
            }
            sink.complete();
        });



    }

    HashMap<String, HashMap<String, List<String >>> map = new HashMap<>();


    @GetMapping(path="/employee")
    public Employee getEmployeeById(Integer id){
        return employeeRepository.findById(id).get();
    }

    @PostMapping(path="/employee")
    public Employee saveEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }
}
