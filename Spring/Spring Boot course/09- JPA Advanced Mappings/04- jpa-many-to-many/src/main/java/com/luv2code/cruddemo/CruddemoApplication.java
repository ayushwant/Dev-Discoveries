package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.luv2code.cruddemo.entity.*;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {

		return runner -> {
//			createCourseAndStudents(appDAO);
//			findCourseAndStudents(appDAO);
//			findStudentAndCourses(appDAO);
//			addMoreCoursesForStudent(appDAO);
//			deleteCourse(appDAO);
//			deleteStudent(appDAO);
			System.out.println("Run a method!");
		};
	}

	private void deleteStudent(AppDAO appDAO) {

		// To delete associations from DB, it only needs to delete the association from the join table.
		// Only join table knows the association between the student and course.

		int theId = 1;
		System.out.println("Deleting student id: " + theId);

		appDAO.deleteStudentById(theId);

		System.out.println("Done!");
	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {

		// To delete associations from DB, it only needs to delete the association from the join table.
		// Only join table knows the association between the student and course.
		
		int theId = 2;
		Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

		// create more courses
		Course tempCourse1 = new Course("Rubik's Cube - How to Speed Cube");
		Course tempCourse2 = new Course("Atari 2600 - Game Development");

		// add courses to student
		tempStudent.addCourse(tempCourse1);
		tempStudent.addCourse(tempCourse2);

		System.out.println("Updating student: " + tempStudent);
		System.out.println("associated courses: " + tempStudent.getCourses());

		appDAO.update(tempStudent);

		System.out.println("Done!");
	}
	private void findStudentAndCourses(AppDAO appDAO) {

		int theId = 2;
		Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

		System.out.println("Loaded student: " + tempStudent);
		System.out.println("Courses: " + tempStudent.getCourses());

		System.out.println("Done!");
	}

	private void findCourseAndStudents(AppDAO appDAO) {

		int theId = 10;
		Course tempCourse = appDAO.findCourseAndStudentsByCourseId(theId);

		System.out.println("Loaded course: " + tempCourse);
		System.out.println("Students: " + tempCourse.getStudents());

		System.out.println("Done!");
	}

	private void createCourseAndStudents(AppDAO appDAO) {

		// create a course
		Course tempCourse = new Course("Pacman - How To Score One Million Points");

		// create the students
		Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
		Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");

		// add students to the course
		tempCourse.addStudent(tempStudent1);
		tempCourse.addStudent(tempStudent2);

		// save the course and associated students
		System.out.println("Saving the course: " + tempCourse);
		System.out.println("associated students: " + tempCourse.getStudents());

		appDAO.save(tempCourse);

		System.out.println("Done!");

		/*

		Saves course, student independently in the DB, and then saves the association in the join table.

		Saving the course: Course{id=0, title='Pacman - How To Score One Million Points'}
associated students: [Student{id=0, firstName='John', lastName='Doe', email='john@luv2code.com'}, Student{id=0, firstName='Mary', lastName='Public', email='mary@luv2code.com'}]
Hibernate:
    insert
    into
        course
        (instructor_id, title)
    values
        (?, ?)
2024-04-15T13:04:22.204+05:30 TRACE 8679 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [null]
2024-04-15T13:04:22.204+05:30 TRACE 8679 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [Pacman - How To Score One Million Points]
Hibernate:
    insert
    into
        student
        (email, first_name, last_name)
    values
        (?, ?, ?)
2024-04-15T13:04:22.209+05:30 TRACE 8679 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:VARCHAR) <- [john@luv2code.com]
2024-04-15T13:04:22.209+05:30 TRACE 8679 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [John]
2024-04-15T13:04:22.209+05:30 TRACE 8679 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (3:VARCHAR) <- [Doe]
Hibernate:
    insert
    into
        student
        (email, first_name, last_name)
    values
        (?, ?, ?)
2024-04-15T13:04:22.210+05:30 TRACE 8679 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:VARCHAR) <- [mary@luv2code.com]
2024-04-15T13:04:22.210+05:30 TRACE 8679 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:VARCHAR) <- [Mary]
2024-04-15T13:04:22.210+05:30 TRACE 8679 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (3:VARCHAR) <- [Public]
Hibernate:
    insert
    into
        course_student
        (course_id, student_id)
    values
        (?, ?)
2024-04-15T13:04:22.214+05:30 TRACE 8679 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [10]
2024-04-15T13:04:22.214+05:30 TRACE 8679 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:INTEGER) <- [1]
Hibernate:
    insert
    into
        course_student
        (course_id, student_id)
    values
        (?, ?)
2024-04-15T13:04:22.215+05:30 TRACE 8679 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:INTEGER) <- [10]
2024-04-15T13:04:22.215+05:30 TRACE 8679 --- [           main] org.hibernate.orm.jdbc.bind              : binding parameter (2:INTEGER) <- [2]
Done!
		 */
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {

		// get the course and reviews
		int theId = 10;
		Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

		// print the course
		System.out.println(tempCourse);

		// print the reviews
		System.out.println(tempCourse.getReviews());
	}

	private void createCourseAndReviews(AppDAO appDAO) {

		/*
		Will create/save the course and review independently,
		then set the review to the course, and update the course.
		 */
		// create a course
		Course tempCourse = new Course("Pacman - How To Score One Million Points");

		// add some reviews
		tempCourse.addReview(new Review("Great course ... loved it!"));
		tempCourse.addReview(new Review("Cool course, job well done."));
		tempCourse.addReview(new Review("What a dumb course, you are an idiot!"));

		// save the course ... and leverage the cascade all
		System.out.println("Saving the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		appDAO.save(tempCourse);

		System.out.println("Done!");
	}

	private void deleteCourse(AppDAO appDAO) {

		int theId = 10;

		System.out.println("Deleting course id: " + theId);

		appDAO.deleteCourseById(theId);

		System.out.println("Done!");
	}

	private void updateCourse(AppDAO appDAO) {

		int theId = 10;

		// find the course
		System.out.println("Finding course id: " + theId);
		Course tempCourse = appDAO.findCourseById(theId);

		// update the course
		System.out.println("Updating course id: " + theId);
		tempCourse.setTitle("Enjoy the Simple Things");

		appDAO.update(tempCourse);

		System.out.println("Done!");
	}

	private void updateInstructor(AppDAO appDAO) {

		// Update also calls other associated entities in the same transaction, and updates them as well if needed.
		/*
		Hibernate:
			select
				i1_0.id,
				i1_0.email,
				i1_0.first_name,
				id1_0.id,
				id1_0.hobby,
				id1_0.youtube_channel,
				i1_0.last_name,
				c1_0.instructor_id,
				c1_0.id,
				c1_0.title
			from
				instructor i1_0
			left join
				instructor_detail id1_0
					on id1_0.id=i1_0.instructor_detail_id
			left join
				course c1_0
					on i1_0.id=c1_0.instructor_id
			where
				i1_0.id=?
		 */

		int theId = 1;

		// find the instructor
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		// update the instructor
		System.out.println("Updating instructor id: " + theId);
		tempInstructor.setLastName("TESTER");

		appDAO.update(tempInstructor);

		System.out.println("Done!");
	}

	private void findInstructorAndOthersByIdJoinFetch(AppDAO appDAO) {
		/*
		Hibernate:
			select
				i1_0.id,
				c1_0.instructor_id,
				c1_0.id,
				c1_0.title,
				i1_0.email,
				i1_0.first_name,
				id1_0.id,
				id1_0.hobby,
				id1_0.youtube_channel,
				i1_0.last_name
			from
				instructor i1_0
			join
				course c1_0
					on i1_0.id=c1_0.instructor_id
			join
				instructor_detail id1_0
					on id1_0.id=i1_0.instructor_detail_id
			where
				i1_0.id=?
		 */

		int theId = 1;

		// find the instructor
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorAndOthersByIdJoinFetch(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());

		System.out.println("Done!");
	}


	// Example of LAZY loading of courses for an instructor
	private void findCoursesForInstructorLazily(AppDAO appDAO) {

		int theId = 1;
		// find instructor
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);

		// find courses for instructor
		System.out.println("Finding courses for instructor id: " + theId);
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		// associate the objects
		tempInstructor.setCourses(courses);

		System.out.println("the associated courses: " + tempInstructor.getCourses());

		System.out.println("Done!");
	}


	// Example of EAGER loading of courses for an instructor
	private void findInstructorWithCoursesEagerly(AppDAO appDAO) {

		/*

		find by ID: fetches all the related entities in a single query. This is when EAGER fetch.
		Hibernate:
    select
        i1_0.id,
        i1_0.email,
        i1_0.first_name,
        id1_0.id,
        id1_0.hobby,
        id1_0.youtube_channel,
        i1_0.last_name,
        c1_0.instructor_id,
        c1_0.id,
        c1_0.title
    from
        instructor i1_0
    left join
        instructor_detail id1_0
            on id1_0.id=i1_0.instructor_detail_id
    left join
        course c1_0
            on i1_0.id=c1_0.instructor_id
    where
        i1_0.id=?
		 */

		int theId = 1;
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {

		// create the instructor
		Instructor tempInstructor =
				new Instructor("Susan", "Public", "susan.public@luv2code.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://www.youtube.com",
						"Video Games");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// create some courses
		Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
		Course tempCourse2 = new Course("The Pinball Masterclass");

		// add courses to instructor
		tempInstructor.addCourse(tempCourse1);
		tempInstructor.addCourse(tempCourse2);

		// save the instructor
		//
		// NOTE: this will ALSO save the courses
		// because of CascadeType.PERSIST
		//
		System.out.println("Saving instructor: " + tempInstructor);
		System.out.println("The courses: " + tempInstructor.getCourses());
		appDAO.save(tempInstructor);

		System.out.println("Done!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {

		int theId = 3;
		System.out.println("Deleting instructor detail id: " + theId);

		appDAO.deleteInstructorDetailById(theId);

		System.out.println("Done!");
	}

	private void findInstructorDetail(AppDAO appDAO) {

		// get the instructor detail object
		int theId = 2;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

		// print the instructor detail
		System.out.println("tempInstructorDetail: " + tempInstructorDetail);

		// print the associated instructor
		System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

		System.out.println("Done!");
	}

	private void deleteInstructor(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Deleting instructor id: " + theId);

		appDAO.deleteInstructorById(theId);

		System.out.println("Done!");
	}

	private void findInstructor(AppDAO appDAO) {

		int theId = 2;
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated instructorDetail only: " + tempInstructor.getInstructorDetail());

	}

	private void createInstructor(AppDAO appDAO) {

		/*
		// create the instructor
		Instructor tempInstructor =
				new Instructor("Chad", "Darby", "darby@luv2code.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://www.luv2code.com/youtube",
						"Luv 2 code!!!");
		*/

		// create the instructor
		Instructor tempInstructor =
				new Instructor("Madhu", "Patel", "madhu@luv2code.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://www.luv2code.com/youtube",
						"Guitar");

		// associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor
		//
		// NOTE: this will ALSO save the details object
		// because of CascadeType.ALL
		//
		System.out.println("Saving instructor: " + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done!");
	}
}








