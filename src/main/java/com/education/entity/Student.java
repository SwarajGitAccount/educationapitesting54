package com.education.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="student_info")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String courseName;
    private String emailId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="instructor_id")
    private Instructor instructor;
}
