package com.chad.springboot.advancedhibernatemapping;

import com.chad.springboot.advancedhibernatemapping.dao.AppDao;
import com.chad.springboot.advancedhibernatemapping.entity.Course;
import com.chad.springboot.advancedhibernatemapping.entity.CourseReview;
import com.chad.springboot.advancedhibernatemapping.entity.Instructor;
import com.chad.springboot.advancedhibernatemapping.entity.InstructorDetail;
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
			//saveInstructor(appDao);

			//int id =2;findInstructor(appDao,id);

			//int id =1;removeInstructor(appDao,id);
			//int id=3;	findInstructorDetailById(appDao, id);
			//int id=4;	deleteInstructorDetailById(appDao, id);


			//createInstructorWithCourses(appDao);

			//int id =1;findInstructorWithCourses(id, appDao);


			//findCoursesForInstructor(1,appDao);

	     	 //findInstructorUsingJoinFetch(1,appDao);

     		//	updateInstructor(1,appDao);
			//updateCourse(10, appDao);

			//deleteInstructor(1,appDao);

			//deleteCourse(10,appDao);

			//saveCourseAndReviews(appDao);

		 ///	findCourseWithReviews(10,appDao);

			//findCourseWithReviewsUsingFetch(13,appDao);

			deleteCourse(12,appDao);


		};

	}



	/// create instructor with courses
	// 1
	public void createInstructorWithCourses(AppDao appDao){
		Instructor instructor= new Instructor("Chad","darby","chad@gmail.com");
		InstructorDetail instructorDetail = new InstructorDetail("http//:www.youtube.com/luv2code", "Love to code!!");
		instructor.setInstructorDetail(instructorDetail);

		Course course =new Course("[new]Spring boot ");
		Course course2=new Course("Hibernate advanced");
		Course course3=new Course("Spring boot testing");

		instructor.add(course);
		instructor.add(course2);
		instructor.add(course3);

		appDao.save(instructor);


	}

	// 2

	private void findInstructorWithCourses(int id, AppDao appDao) {
		Instructor instructor=appDao.findById(id);
		System.out.println("Instructor itself:"+instructor);
		System.out.println("Instructor detail:"+instructor.getInstructorDetail());
		System.out.println("Instructor courses:"+instructor.getCourses());
	}

	// 3

	public void findCoursesForInstructor(int id,AppDao appDao){
		Instructor instructor=appDao.findById(id);
		System.out.println("Instructor itself:"+instructor);
		System.out.println("Instructor detail:"+instructor.getInstructorDetail());

		// here i hold the courses in a reference var and i'm free , i can do anything with it
		List<Course> courses =appDao.findCoursesForInstructor(id);
		//	System.out.println("Instructor courses:"+courses);
		instructor.setCourses(courses);
		System.out.println("courses for instructor: "+instructor.getCourses());

	}


	// 4
	public void  findInstructorUsingJoinFetch(int id , AppDao appDao) {
		Instructor instructor = appDao.findInstructorByIdJoinFetch(id);
		System.out.println("instructor: "+ instructor);
		System.out.println("instructor Details: "+instructor.getInstructorDetail());
		System.out.println("Courses"+instructor.getCourses());
	}


	// 5
	public void updateInstructor(int id,AppDao appDao){
		Instructor instructor =appDao.findById(id);

		// update some data for instructor
		instructor.setFirstName("Tester");
		appDao.updateInstructor(instructor);


	}


	//6
	public void updateCourse(int id,AppDao appDao){
		Course course =appDao.findCourseById(id);

		// update some data for instructor

		course.setTitle("Testing");
		appDao.updateCourse(course);

	}





   //7
	private void removeInstructor(AppDao appDao, int id) {

		System.out.println(appDao.delete(id));
	}








  //
	public void saveCourseAndReviews (AppDao appDao){
		Instructor instructor=new Instructor("Jim", "Wilson","wilson@gmail.com");
		InstructorDetail instructorDetail= new InstructorDetail("http//:www.youtube.com/javachampion","Java Champion!");
		instructor.setInstructorDetail(instructorDetail);

		Course course1 =new Course("Java Basics");
		Course course2 =new Course("Exception Handling using java");

		instructor.add(course1);
		instructor.add(course2);

		CourseReview courseReview1=new CourseReview("Amazing!");
		CourseReview courseReview3=new CourseReview("Best resource for java ever!");
		CourseReview courseReview4=new CourseReview("omg it's amazing!");
		CourseReview courseReview2=new CourseReview("Keep going!");
		course1.addReview(courseReview1);
		course1.addReview(courseReview3);
		course1.addReview(courseReview4);
		course2.addReview(courseReview2);

		appDao.saveCourse(course1);
		appDao.saveCourse(course2);

	}


	private void findCourseWithReviews(int id, AppDao appDao) {
		Course course =appDao.findCourseAndReviewsByCourseId(id);
		System.out.println("Course: "+course);
		System.out.println("Course reviews: "+ course.getReviews());

	}

	private void findCourseWithReviewsUsingFetch(int id, AppDao appDao) {
		Course course =appDao.findReviews(id);
		System.out.println("Course: "+course);
		System.out.println("Course reviews: "+ course.getReviews());

	}

	private void deleteCourse (int id, AppDao appDao){
		appDao.deleteCourse(id);

	}


}
