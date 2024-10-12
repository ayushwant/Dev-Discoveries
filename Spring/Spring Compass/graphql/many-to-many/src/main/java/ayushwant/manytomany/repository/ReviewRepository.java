package ayushwant.manytomany.repository;

import ayushwant.manytomany.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
}