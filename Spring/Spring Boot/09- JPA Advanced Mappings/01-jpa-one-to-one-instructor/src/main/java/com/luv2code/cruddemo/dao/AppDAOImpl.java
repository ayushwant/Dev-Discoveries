package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// We need to create only 1 DAO class for both Instructor and InstructorDetail entities.
// This is because there are only 2 classes, and the parent class (Instructor) has a
// cascade ALL with the child class (InstructorDetail). So, it will save both classes into the database.
@Repository
public class AppDAOImpl implements AppDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);

        //saves the related entity first, and then the parent entity.
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
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
         */
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {

        // retrieve the instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        // delete the instructor
        entityManager.remove(tempInstructor);

        // deleted the instructor and then the associated instructorDetail, one by one
    }

    // will find all in a single query

    /**
     select
     id1_0.id,
     id1_0.hobby,
     i1_0.id,
     i1_0.email,
     i1_0.first_name,
     i1_0.last_name,
     id1_0.youtube_channel
     from
     instructor_detail id1_0
     left join
     instructor i1_0
     on id1_0.id=i1_0.instructor_detail_id
     where
     id1_0.id=?
     * @param theId
     * @return
     */
    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }


    /**
     * In this method,
     If we're using CascadeType.ALL, then both the instructor detail and instructor will be deleted.

     However, we have {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}

     So, deleteInstructorDetailById will first fetch the instructorDetail object from the DB.
     Then, it will update the instructor object, and set instructorDetail to null (it's a choice)
     Then, it will delete the instructorDetail object from the DB.

     However, if we don't delete the instructorDetail reference from the instructor object,
     then the instructorDetail object will not be deleted from the DB.
     */
    /*
    Deleting instructor detail id: 4
Hibernate:
    select
        id1_0.id,
        id1_0.hobby,
        i1_0.id,
        i1_0.email,
        i1_0.first_name,
        i1_0.last_name,
        id1_0.youtube_channel
    from
        instructor_detail id1_0
    left join
        instructor i1_0
            on id1_0.id=i1_0.instructor_detail_id
    where
        id1_0.id=?
2024-04-13T21:40:19.033+05:30 TRACE 5798 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [4]

Hibernate:
    update
        instructor
    set
        email=?,
        first_name=?,
        instructor_detail_id=?,
        last_name=?
    where
        id=?
2024-04-13T21:40:19.043+05:30 TRACE 5798 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:VARCHAR) <- [madhu@luv2code.com]
2024-04-13T21:40:19.043+05:30 TRACE 5798 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [Madhu]
2024-04-13T21:40:19.043+05:30 TRACE 5798 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (3:INTEGER) <- [null]
2024-04-13T21:40:19.043+05:30 TRACE 5798 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (4:VARCHAR) <- [Patel]
2024-04-13T21:40:19.043+05:30 TRACE 5798 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (5:INTEGER) <- [4]

Hibernate:
    delete
    from
        instructor_detail
    where
        id=?
     */
    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {

        // retrieve instructor detail
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        // remove the associated object reference
        // break bi-directional link
        //
//        tempInstructorDetail.getInstructor().setInstructorDetail(null);

        // delete the instructor detail
        entityManager.remove(tempInstructorDetail);
    }

    // If we don't break the bi-directional link, then the instructorDetail object will not be deleted from the DB:

    //However, it seems like the bi-directional link between Instructor and InstructorDetail
    // is not being broken before the deletion. This could be causing a problem if there's a
    // constraint in your database schema that prevents an InstructorDetail from being deleted while
    // it's still referenced by an Instructor.

}







