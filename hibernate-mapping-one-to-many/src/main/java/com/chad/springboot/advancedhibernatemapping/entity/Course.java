package com.chad.springboot.advancedhibernatemapping.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
@Setter
@Getter
@NoArgsConstructor
@ToString(exclude = {"instructor", "reviews"})
public class Course {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;


    // many to one here means one instructor owns many courses
    // if you notice that the many is for the class itself
    // i mean i put the many in the thing that is many
    // we put here join column cause in the db there is column called instructor_id
    // if i delete this course , i do not wanna delete the instructor


    /*
     select * from instructor i inner join course c
     on i.id = c.instructor_id;
     */
    @ManyToOne(cascade={CascadeType.DETACH, CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;



    // here we tell the hibernate to go to review table and look at course_id
    @OneToMany(cascade =CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private List<CourseReview> reviews;


    public Course(String title) {
        this.title = title;
    }


    public void addReview(CourseReview courseReview) {
        if (reviews == null) {
            reviews = new ArrayList<>();

        }
        reviews.add(courseReview);

    }



}
