package com.chad.springboot.advancedhibernatemapping.dao;

import com.chad.springboot.advancedhibernatemapping.entity.Course;
import com.chad.springboot.advancedhibernatemapping.entity.Instructor;
import com.chad.springboot.advancedhibernatemapping.entity.InstructorDetail;

import java.util.List;

public interface AppDao {
    void save(Instructor instructor);
    Instructor findById(int id);

    Instructor delete(int id);

    InstructorDetail findInstructorDetailById(int id );
    void deleteInstructorDetailById(int id );


    // one to many relationship
    List<Course> findCoursesForInstructor(int id);


    // using join fetch
    Instructor findInstructorByIdJoinFetch(int id);


    void updateInstructor(Instructor instructor);

    void updateCourse(Course course);

    Course findCourseById(int id);

    void deleteInstructor(int id);

    void deleteCourse(int id);



    // one to many uni
    void saveCourse(Course course);


    Course findCourseAndReviewsByCourseId(int id);


    Course findReviews(int id);


}
