package com.chad.springboot.advancedhibernatemapping.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructor")
@Setter
@Getter
@NoArgsConstructor
@ToString(exclude = "courses")
public class Instructor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    // it's like that select * from
    //instructor i inner join instructor_detail id
    //on i.id = id.id
    //where i.id=2;

    // this is normal join - means to join table instructor_detail based on the id of instructor table
    @JoinColumn(name = "instructor_detail_id")
    @OneToOne(cascade = CascadeType.ALL) // this means any operation happens for instructor will happen to his detail
    private InstructorDetail instructorDetail;



    // that is for bidirectional relationship
    // if I removed this instructor, I will not delete courses
    @OneToMany(mappedBy = "instructor"
            ,cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.DETACH}
    )
    private List<Course> courses;


    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }


    public void add(Course course){
        if (courses==null){
            courses =new ArrayList<>();
        }
        courses.add(course);
        course.setInstructor(this);
    }

    /*@Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", instructorDetail=" + instructorDetail +
                '}';
    }*/


}
