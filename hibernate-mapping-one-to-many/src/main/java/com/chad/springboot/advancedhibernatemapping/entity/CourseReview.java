package com.chad.springboot.advancedhibernatemapping.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "review")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class CourseReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "comment")
    private String comment;


    public CourseReview(String comment) {
        this.comment = comment;
    }


}
