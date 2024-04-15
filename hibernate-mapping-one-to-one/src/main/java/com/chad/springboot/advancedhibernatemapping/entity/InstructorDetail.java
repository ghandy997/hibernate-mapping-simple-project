package com.chad.springboot.advancedhibernatemapping.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "instructor_detail")
@Setter
@Getter
@NoArgsConstructor
@ToString(exclude = "instructor")
public class InstructorDetail {

    // this part is for one-to-one uni relationship
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "youtube_channel")
    private String youtubeChannel;
    @Column(name = "hobby")
    private String hobby;


    // this part is for bi-direction
    // you have no colum in database to make it refers to or joinColumn
    // this refers to instructorDetail field in instructor class
    // mapped by can tells this filed that to use the instructorDetail field from Instructor class
    // to use the info from instructor class @JoinColumn
    // to find the associated constructor
    @OneToOne(mappedBy = "instructorDetail",
            cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    private Instructor instructor;

    public InstructorDetail(String youtubeChannel, String hobby) {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
    }


}
