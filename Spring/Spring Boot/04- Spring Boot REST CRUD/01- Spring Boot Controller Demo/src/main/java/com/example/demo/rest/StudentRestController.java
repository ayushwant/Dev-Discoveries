package com.example.demo.rest;

import com.example.demo.model.Student;
import com.example.demo.model.StudentErrorResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Student getStudent(@PathVariable int ID)
    {
        if(ID<0 || ID>=students.size()){
            throw new StudentNotFoundException("student with ID = " +ID + " not found");
        }

        return students.get(ID);
    }

}
