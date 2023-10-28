package com.ayushwant.jpacruddemo;

import com.ayushwant.jpacruddemo.dao.StudentDAO;
import com.ayushwant.jpacruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpacruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpacruddemoApplication.class, args);
	}

	// CommandLineRunner is from Spring.
	// This will be executed after the Spring beans have been loaded
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) // spring auto injects the StudentDAO for us
	{

		return runner -> {
			createStudent(studentDAO);
		};
	}

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

}
