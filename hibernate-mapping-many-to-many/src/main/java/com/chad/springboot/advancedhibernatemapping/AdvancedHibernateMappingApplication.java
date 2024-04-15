package com.chad.springboot.advancedhibernatemapping;

import com.chad.springboot.advancedhibernatemapping.dao.AppDao;
import com.chad.springboot.advancedhibernatemapping.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class AdvancedHibernateMappingApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedHibernateMappingApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner (AppDao appDao){
		return runner->{
       
			//createCourseWithStudents(appDao);

			//findCourseAndStudent(10,appDao);

			//addMoreCoursesToStudent(1, appDao);

		   //deleteCourse(13,appDao);
			// deleteStudent(2,appDao);

		};

	}



	private void createCourseWithStudents(AppDao appDao) {
		Course course =new Course("Spring boot Advanced");
		Student abdul = new Student("AbdulMajeed", "Ghandy" , "ghandy97@gmail.com");
		Student ahmed = new Student("Ahmed", "Hassan" , "hassan@gmail.com");

		course.addStudent(abdul);
		course.addStudent(ahmed);


		appDao.saveCourse(course);
	}

	public void findCourseAndStudent (int id, AppDao appDao){
		Course course =appDao.findCourseAndStudentById(id);

		System.out.println("Course : "+course);
		System.out.println("Students:  "+ course.getStudents());

	}
	private void addMoreCoursesToStudent(int id,AppDao appDao) {
		Student student = appDao.findStudentAndCourseById(id);

		Course testing =new Course("Spring boot Testing");
		Course java =new Course("OOP java advanced");
		Course agile =new Course("Agile Crush course");


		student.addCourse(testing);
		student.addCourse(java);
		student.addCourse(agile);

		appDao.update(student);
	}

	private void deleteCourse(int i, AppDao appDao) {
	//	Course course = appDao.findCourseById(i);
		appDao.deleteCourse(i);

	}

	private void deleteStudent(int i, AppDao appDao) {
		//	Course course = appDao.findCourseById(i);
		appDao.deleteStudent(i);

	}
}
