package com.chad.springboot.advancedhibernatemapping.dao;

import com.chad.springboot.advancedhibernatemapping.entity.Course;
import com.chad.springboot.advancedhibernatemapping.entity.Instructor;
import com.chad.springboot.advancedhibernatemapping.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Table;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDaoImpl implements AppDao{
    private final EntityManager entityManager;
    @Autowired
    public AppDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findById(int id) {
        // this will fetch the instructor detail to
        // cause the default fetching type of one to one is eager
        // it's like that select * from
        //instructor i inner join instructor_detail id
        //on i.id = id.id
        //where i.id=2;
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public Instructor delete(int id) {
        Instructor instructor=entityManager.find(Instructor.class, id);
        entityManager.remove(instructor);
        return instructor;
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }


    // look that removing from any two parts will remove at the other part
    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail=entityManager.find(InstructorDetail.class, id);

        // break the bidirectional association
      // instructorDetail.getInstructor().setInstructorDetail(null);
        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesForInstructor(int theId) {
        // select c.id, c.title, c.instructor_id from instructor i inner join course c
        // on i.id = c.instructor_id
        // where i.id=2;

        TypedQuery<Course> query=entityManager.createQuery("FROM Course WHERE instructor.id =:data", Course.class);
        query.setParameter("data", theId);

        return query.getResultList();
    }


    // that get instructor with all courses, it is eager loading

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {

        /*
         SELECT * FROM instructor i
         inner JOIN instructor_detail id ON i.instructor_detail_id = id.id
         inner JOIN course c ON i.id = c.instructor_id
         WHERE i.id = :data
        */
        TypedQuery<Instructor> query= entityManager.createQuery(
             "select i from Instructor i " +
                "join fetch i.courses " +
                "join fetch i.instructorDetail "+
                "where i.id=: data", Instructor.class);

        query.setParameter("data", id);

        return query.getSingleResult();

    }

    @Override
    @Transactional
    public void updateInstructor(Instructor instructor) {
       entityManager.merge(instructor);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }


    @Override
    @Transactional
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }

    @Override
    @Transactional
    public void deleteInstructor(int id) {
       Instructor instructor=entityManager.find(Instructor.class, id);

       List<Course> courses= instructor.getCourses();

    for(Course course :courses){
        course.setInstructor(null);
    }
       entityManager.remove(instructor);

    }

    @Override
    @Transactional
    public void deleteCourse(int id) {
     Course course = entityManager.find(Course.class, id);

     entityManager.remove(course);
    }



    /////////////////////////////////////// one to many uni//////////////////////////
    @Override
    @Transactional
    public void saveCourse(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {
        return entityManager.find(Course.class, id);
    }
   /*
       "select i from Instructor i " +
                "join fetch i.courses " +
                "join fetch i.instructorDetail "+
                "where i.id=: data", Instructor.class);
   * */

    @Override
    public Course findReviews(int id) {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c" +
                " join fetch c.reviews" +
                " where c.id=:data", Course.class);

        query.setParameter("data", id);

        return query.getSingleResult();
    }


}
