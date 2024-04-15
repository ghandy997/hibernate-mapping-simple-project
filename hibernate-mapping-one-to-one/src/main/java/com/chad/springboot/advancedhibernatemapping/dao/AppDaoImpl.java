package com.chad.springboot.advancedhibernatemapping.dao;

import com.chad.springboot.advancedhibernatemapping.entity.Instructor;
import com.chad.springboot.advancedhibernatemapping.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AppDaoImpl implements AppDao{
    private EntityManager entityManager;
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
        instructorDetail.getInstructor().setInstructorDetail(null);
        entityManager.remove(instructorDetail);
    }
}
