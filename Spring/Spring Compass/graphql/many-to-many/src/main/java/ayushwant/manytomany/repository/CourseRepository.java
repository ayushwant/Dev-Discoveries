package ayushwant.manytomany.repository;

import ayushwant.manytomany.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface CourseRepository extends JpaRepository<Course, Integer> {
}
