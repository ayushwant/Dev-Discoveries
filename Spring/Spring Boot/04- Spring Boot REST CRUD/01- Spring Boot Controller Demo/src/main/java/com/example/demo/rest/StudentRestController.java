package com.example.demo.rest;

import com.example.demo.model.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class StudentRestController
{
    List<Student> students;

    // use @PostConstruct to load the data only once.
    @PostConstruct
    public void loadData()
    {
        students = new ArrayList<>();

        students.add(new Student("Poornima", "Patel"));
        students.add(new Student("John", "Kennedy"));
        students.add(new Student("George", "Bush"));
    }

    @GetMapping("students")
    // the name of method doesn't matter. Only the URL endpoint matters
    public List<Student> getStudents()
    {
        return students;
    }

    @GetMapping("students/{ID}")
    // the name of method doesn't matter. Only the URL endpoint matters
    public Student getStudent(@PathVariable int ID){
        return students.get(ID);
    }

}
