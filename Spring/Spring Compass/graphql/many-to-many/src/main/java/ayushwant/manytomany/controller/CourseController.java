package ayushwant.manytomany.controller;

import ayushwant.manytomany.entity.Course;
import ayushwant.manytomany.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @QueryMapping
    public List<Course> courses() {
        return courseService.getAllCourses();
    }

    @QueryMapping
    public Optional<Course> course(@Argument Integer id) {
        return courseService.getCourseById(id);
    }
}