package com.example.demo.rest;

import com.example.demo.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class StudentRestController
{
    @GetMapping("students")
    public ArrayList<Student> getStudents()
    {
        ArrayList<Student> students = new ArrayList<>();

        students.add(new Student("Poornima", "Patel"));
        students.add(new Student("John", "Kennedy"));
        students.add(new Student("George", "Bush"));

        return students;
    }

}
