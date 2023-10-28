package com.ayushwant.jpacruddemo;

import com.ayushwant.jpacruddemo.dao.StudentDAO;
import com.ayushwant.jpacruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class JpacruddemoApplication {

	public static void main(String[] args) {
		System.out.println("In main method of JpacruddemoApplication");
		SpringApplication.run(JpacruddemoApplication.class, args);
	}

	// CommandLineRunner is from Spring.
	// This will be executed after the Spring beans have been loaded
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) // spring auto-injects the StudentDAO for us when it calls this method
	{
		System.out.println("In commandLineRunner method");
		return runner -> {
			System.out.println("In runner lambda of commandLineRunner");

//			createMultipleStudents(studentDAO);
//			System.out.println(getAllStudents(studentDAO));

//			System.out.println(getAllStudentsByLastName(studentDAO));
//			updateStudent(studentDAO);

			deleteStudent(studentDAO);
		};
	}

	private void deleteStudent(StudentDAO studentDAO) {
		// delete the student
		int studentId = 3;
		System.out.println("Deleting student id: " + studentId);

		studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {
		// retrieve student based on the id: primary key
		int studentId = 1;
		Student myStudent = studentDAO.findByID(studentId);

		// change first name to "Scooby"
		myStudent.setFirstName("Scooby");
		studentDAO.update(myStudent);

		// display updated student
		System.out.println("Updated student: " + myStudent);
		System.out.println(studentDAO.findByID(1));
	}

	private List<Student> getAllStudentsByLastName(StudentDAO studentDAO) {
		return studentDAO.findByLastName();
	}

	private List<Student> getAllStudents(StudentDAO studentDAO)
	{
		System.out.println("In method findAll of commandLinerRunner method");

		return studentDAO.findAll();
	}

	private Student readStudent(StudentDAO studentDAO)
	{
        return studentDAO.findByID(1);
	}

	//mysql handles auto increment and unique property of the ID
	private void createStudent(StudentDAO studentDAO)
	{
		//create the student object
		System.out.println("Creating new student object ...");
		Student tempStudent = new Student("Paul", "Doe", "paul@luv2code.com");

		// save the student object
		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent);

		// display id of the saved student
		// it gets the ID, even though we did not explicitly create an ID.
		// happens coz we're using DAO and Entity
		System.out.println("Saved student. Generated id: " + tempStudent.getId());

	}

	private void createMultipleStudents(StudentDAO studentDAO) {

		// create multiple students
		System.out.println("Creating 3 student objects ...");
		Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
		Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");
		Student tempStudent3 = new Student("Bonita", "Applebum", "bonita@luv2code.com");

		// save the student objects
		System.out.println("Saving the students ...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

}
