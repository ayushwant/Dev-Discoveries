package ayushwant.manytomany.repository;

import ayushwant.manytomany.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
}