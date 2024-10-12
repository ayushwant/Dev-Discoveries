package com.luv2code.cruddemo.jparepository;

import com.luv2code.cruddemo.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer>{

    // the findAll of JPARepository doesn't fetch all the associated entities from the DB
    // It fetches the parent, and then the child entities one by one.
    // Leads to N+1 problem.
    /**
     findAll():
     Hibernate:
     select
     i1_0.id,
     i1_0.email,
     i1_0.first_name,
     i1_0.instructor_detail_id,
     i1_0.last_name
     from
     instructor i1_0
     Hibernate:
     select
     id1_0.id,
     id1_0.hobby,
     id1_0.youtube_channel
     from
     instructor_detail id1_0
     where
     id1_0.id=?
     */
    @Override
    List<Instructor> findAll();





    // however, findByID does fetch the associated entities from the DB in a single query.
    /**
     * findByID:
     select
     i1_0.id,
     i1_0.email,
     i1_0.first_name,
     id1_0.id,
     id1_0.hobby,
     id1_0.youtube_channel,
     i1_0.last_name
     from
     instructor i1_0
     left join
     instructor_detail id1_0
     on id1_0.id=i1_0.instructor_detail_id
     where
     i1_0.id=?
     */

    @Override
    Optional<Instructor> findById(Integer integer);


    // deleteById first fetches the entity as well as the associated entities from the DB in a single query.
    // then deleted them one by one.
    /**
     Hibernate:
     select
     i1_0.id,
     i1_0.email,
     i1_0.first_name,
     id1_0.id,
     id1_0.hobby,
     id1_0.youtube_channel,
     i1_0.last_name
     from
     instructor i1_0
     left join
     instructor_detail id1_0
     on id1_0.id=i1_0.instructor_detail_id
     where
     i1_0.id=?
     2024-04-13T21:17:37.303+05:30 TRACE 5668 --- [nio-8080-exec-1] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [2]

     Hibernate:
     delete
     from
     instructor
     where
     id=?
     2024-04-13T21:17:37.313+05:30 TRACE 5668 --- [nio-8080-exec-1] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [2]

     Hibernate:
     delete
     from
     instructor_detail
     where
     id=?
     2024-04-13T21:17:37.314+05:30 TRACE 5668 --- [nio-8080-exec-1] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [2]

     * @param integer
     */
    @Override
    void deleteById(Integer integer);
}
