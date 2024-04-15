package com.chad.springboot.advancedhibernatemapping.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "instructor")
@Setter
@Getter
@NoArgsConstructor
@ToString(exclude ="instructorDetail")
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

    // if you want to see the details of instructor
    // you should do that instructor.getInstructorDetails();
    // but till now you can not do instructorDetail.getInstructor(); cause it's uni-dir relationship

    // this is normal join - means to join table instructor_detail based on the id of instructor table
    // by default no cascading

    @JoinColumn(name = "instructor_detail_id")
    @OneToOne(cascade = CascadeType.ALL) // this means any operation happens for instructor will happen to this detail
    private InstructorDetail instructorDetail;


    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
