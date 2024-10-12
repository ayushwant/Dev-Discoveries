package ayushwant.manytomany.repository;

import ayushwant.manytomany.entity.CourseStudent;
import ayushwant.manytomany.entity.CourseStudentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseStudentRepository extends JpaRepository<CourseStudent, CourseStudentId> {
}
