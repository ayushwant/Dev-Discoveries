package ayushwant.manytomany.service;

import ayushwant.manytomany.entity.Course;
import ayushwant.manytomany.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;


//    @Tool(description = "Get all courses JSON")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

//    @Tool(description = "Get course by ID")
    public Optional<Course> getCourseById(Integer id) {
        return courseRepository.findById(id);
    }

    @Tool(description = "Get all courses as string")
    public String getAllCoursesAsString() {
        return courseRepository.findAll().stream()
                .map(Course::toString)
                .reduce("", (a, b) -> a + "\n" + b);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {)
            // This is where you can run the task asynchronously
            System.out.println("Running in a separate thread");
        });
    }
}
