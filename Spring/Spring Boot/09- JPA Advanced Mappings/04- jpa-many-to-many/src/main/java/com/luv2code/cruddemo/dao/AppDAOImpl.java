package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * This will now first save the instructor detail and then the instructor, and set the instructor_detail_id
     * Now the instructor object has a list of courses, but it's not at actual column in the DB.
     * So the courses will be created after the instructor, and the instructor_id will be set in the course table.
     *
     * Course table has a reference to instructor table, so it is a many-to-one relationship, parent basically.
     * @param theInstructor
     */
    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {

        /*
        Deleting instructor id: 1
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
2024-04-15T12:21:42.435+05:30 TRACE 7588 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
Hibernate:
    select
        c1_0.instructor_id,
        c1_0.id,
        c1_0.title
    from
        course c1_0
    where
        c1_0.instructor_id=?
2024-04-15T12:21:42.444+05:30 TRACE 7588 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
Hibernate:
    update
        course
    set
        instructor_id=?,
        title=?
    where
        id=?
2024-04-15T12:21:42.453+05:30 TRACE 7588 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [null]
2024-04-15T12:21:42.453+05:30 TRACE 7588 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [Enjoy the Simple Things]
2024-04-15T12:21:42.453+05:30 TRACE 7588 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (3:INTEGER) <- [10]
Hibernate:  Only updates course coz of cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                          CascadeType.DETACH, CascadeType.REFRESH}
    update
        course
    set
        instructor_id=?,
        title=?
    where
        id=?
2024-04-15T12:21:42.454+05:30 TRACE 7588 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [null]
2024-04-15T12:21:42.455+05:30 TRACE 7588 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [The Pinball Masterclass]
2024-04-15T12:21:42.455+05:30 TRACE 7588 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (3:INTEGER) <- [11]
Hibernate:
    delete
    from
        instructor
    where
        id=?
2024-04-15T12:21:42.456+05:30 TRACE 7588 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
Hibernate: deletes instructor_detail coz of cascade type all
    delete
    from
        instructor_detail
    where
        id=?
         */

        // retrieve the instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        // get the courses
        List<Course> courses = tempInstructor.getCourses();

        // break association of all courses for the instructor. one by one.
        for (Course tempCourse : courses) {
            tempCourse.setInstructor(null);
        }

        // delete the instructor
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {

        // retrieve instructor detail
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        // remove the associated object reference
        // break bi-directional link
        //
        tempInstructorDetail.getInstructor().setInstructorDetail(null);

        // delete the instructor detail
        entityManager.remove(tempInstructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {

        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class);
        query.setParameter("data", theId);

        // execute query
        List<Course> courses = query.getResultList();

        return courses;
    }

    @Override
    public Instructor findInstructorAndOthersByIdJoinFetch(int theId) {

        // create query
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i "
                        + "JOIN FETCH i.courses "
                        + "JOIN FETCH i.instructorDetail "
                        + "where i.id = :data", Instructor.class);
        query.setParameter("data", theId);

        // execute query
        Instructor instructor = query.getSingleResult();

        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);
    }


    @Override
    @Transactional
    public void update(Course tempCourse) {
        /*
        Updating course id: 10
Hibernate:
    select
        c1_0.id,
        i1_0.id,
        i1_0.email,
        i1_0.first_name,
        id1_0.id,
        id1_0.hobby,
        id1_0.youtube_channel,
        i1_0.last_name,
        c1_0.title
    from
        course c1_0
    left join
        instructor i1_0
            on i1_0.id=c1_0.instructor_id
    left join
        instructor_detail id1_0
            on id1_0.id=i1_0.instructor_detail_id
    where
        c1_0.id=?
2024-04-15T12:06:21.964+05:30 TRACE 7435 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [10]
Hibernate:
    select
        c1_0.instructor_id,
        c1_0.id,
        c1_0.title
    from
        course c1_0
    where
        c1_0.instructor_id=?
2024-04-15T12:06:21.967+05:30 TRACE 7435 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [1]
Hibernate:
    update
        course
    set
        instructor_id=?,
        title=?
    where
        id=?
         */
        entityManager.merge(tempCourse);
    }

    @Override
    public Course findCourseById(int theId) {
        /*

        Also finds the instructor and instructor_detail for the course.
        This is coz instructor is eager fetch in course, and instructor_detail is eager fetch in instructor.
        Hibernate:
    select
        c1_0.id,
        i1_0.id,
        i1_0.email,
        i1_0.first_name,
        id1_0.id,
        id1_0.hobby,
        id1_0.youtube_channel,
        i1_0.last_name,
        c1_0.title
    from
        course c1_0
    left join
        instructor i1_0
            on i1_0.id=c1_0.instructor_id
    left join
        instructor_detail id1_0
            on id1_0.id=i1_0.instructor_detail_id
    where
        c1_0.id=?
         */
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {


        // retrieve the course
        Course tempCourse = entityManager.find(Course.class, theId);

        // delete the course
        entityManager.remove(tempCourse);

        /*
        FEtches all coz of EAGER.
        Deleted only course coz of cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                          CascadeType.DETACH, CascadeType.REFRESH}
        Deleting course id: 10
Hibernate:
    select
        c1_0.id,
        i1_0.id,
        i1_0.email,
        i1_0.first_name,
        id1_0.id,
        id1_0.hobby,
        id1_0.youtube_channel,
        i1_0.last_name,
        c1_0.title
    from
        course c1_0
    left join
        instructor i1_0
            on i1_0.id=c1_0.instructor_id
    left join
        instructor_detail id1_0
            on id1_0.id=i1_0.instructor_detail_id
    where
        c1_0.id=?
2024-04-15T12:25:33.656+05:30 TRACE 7681 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [10]
Hibernate:
    delete
    from
        course
    where
        id=?
         */
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {

        /*
        Hibernate:
    select
        c1_0.id,
        c1_0.instructor_id,
        r1_0.course_id,
        r1_0.id,
        r1_0.comment,
        c1_0.title
    from
        course c1_0
    join
        review r1_0
            on c1_0.id=r1_0.course_id
    where
        c1_0.id=?
         */
        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        + "JOIN FETCH c.reviews "
                        + "where c.id = :data", Course.class);

        query.setParameter("data", theId);

        // execute query
        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int theId) {

        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        + "JOIN FETCH c.students "
                        + "where c.id = :data", Course.class);

        query.setParameter("data", theId);

        // execute query
        Course course = query.getSingleResult();

        return course;

        /*
        Hibernate:
    select
        c1_0.id,
        c1_0.instructor_id,
        s1_0.course_id,
        s1_1.id,
        s1_1.email,
        s1_1.first_name,
        s1_1.last_name,
        c1_0.title
    from
        course c1_0
    join
        course_student s1_0
            on c1_0.id=s1_0.course_id
    join
        student s1_1
            on s1_1.id=s1_0.student_id
    where
        c1_0.id=?
         */
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int theId) {

        // create query
        TypedQuery<Student> query = entityManager.createQuery(
                "select s from Student s "
                        + "JOIN FETCH s.courses "
                        + "where s.id = :data", Student.class);

        query.setParameter("data", theId);

        // execute query
        Student student = query.getSingleResult();

        return student;

        /*
            select
        s1_0.id,
        c1_0.student_id,
        c1_1.id,
        c1_1.instructor_id,
        c1_1.title,
        s1_0.email,
        s1_0.first_name,
        s1_0.last_name
    from
        student s1_0
    join
        course_student c1_0
            on s1_0.id=c1_0.student_id
    join
        course c1_1
            on c1_1.id=c1_0.course_id
    where
        s1_0.id=?
         */
    }

    @Override
    @Transactional
    public void update(Student tempStudent) {
        entityManager.merge(tempStudent);
    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {

        // retrieve the student
        Student tempStudent = entityManager.find(Student.class, theId);

        // delete the student
        entityManager.remove(tempStudent);
    }
}







