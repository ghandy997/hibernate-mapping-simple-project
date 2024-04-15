package com.chad.springboot.advancedhibernatemapping.dao;

import com.chad.springboot.advancedhibernatemapping.entity.Instructor;
import com.chad.springboot.advancedhibernatemapping.entity.InstructorDetail;

public interface AppDao {
    void save(Instructor instructor);
    Instructor findById(int id);

    Instructor delete(int id);

    InstructorDetail findInstructorDetailById(int id );
    void deleteInstructorDetailById(int id );
}
