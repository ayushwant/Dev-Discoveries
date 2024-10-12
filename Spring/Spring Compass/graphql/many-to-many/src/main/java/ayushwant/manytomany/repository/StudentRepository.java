package ayushwant.manytomany.repository;

import ayushwant.manytomany.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}