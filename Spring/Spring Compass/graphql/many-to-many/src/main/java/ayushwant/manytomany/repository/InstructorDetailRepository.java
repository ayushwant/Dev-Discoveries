package ayushwant.manytomany.repository;

import ayushwant.manytomany.entity.InstructorDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface InstructorDetailRepository extends JpaRepository<InstructorDetail, Integer> {
}