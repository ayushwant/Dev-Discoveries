package com.luv2code.cruddemo.controller;

import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.jparepository.InstructorRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
@AllArgsConstructor
public class Controller {

    InstructorRepository instructorRepository;
    @GetMapping("instructor")
    public List<Instructor> getInstructor() {
        return instructorRepository.findAll();
    }

    @GetMapping("instructorById")
    public Instructor getInstructorById(@RequestParam Integer id) {
        return instructorRepository.findById(id).get();
    }

    @GetMapping("deleteInstructor")
    public Instructor deleteInstructor(@RequestParam Integer id) {
        instructorRepository.deleteById(id);

        return new Instructor(); // empty instructor
    }

}
