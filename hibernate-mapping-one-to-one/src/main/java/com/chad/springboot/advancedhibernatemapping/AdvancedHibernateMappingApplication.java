package com.chad.springboot.advancedhibernatemapping;

import com.chad.springboot.advancedhibernatemapping.dao.AppDao;
import com.chad.springboot.advancedhibernatemapping.entity.Instructor;
import com.chad.springboot.advancedhibernatemapping.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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



		};

	}



	public void saveInstructor(AppDao appDao){
		// create an instructor
		Instructor instructor= new Instructor("Chad","darby","chad@gmail.com");
		// create an instructor detail
		InstructorDetail instructorDetail = new InstructorDetail("http//:www.youtube.com/luv2code", "Love to code!!");

		// make the instructor join instructor detail and set values
		instructor.setInstructorDetail(instructorDetail);
		appDao.save(instructor);

	}

	private void removeInstructor(AppDao appDao, int id) {

		System.out.println(appDao.delete(id));
	}

	private void findInstructor(AppDao appDao,int id) {
		Instructor instructor =appDao.findById(id);
		System.out.println("Instructor himself: "+instructor);
		System.out.println("instructor Details: "+instructor.getInstructorDetail());
	}


	// bi-directional
	private void findInstructorDetailById(AppDao appDao,int id) {
		InstructorDetail instructorDetail =appDao.findInstructorDetailById(id);
		System.out.println("InstructorDetail: "+instructorDetail);
		System.out.println("Associated instructor: "+instructorDetail.getInstructor());


	}


	private void deleteInstructorDetailById(AppDao appDao,int id) {
		appDao.deleteInstructorDetailById(id);

	}




}
