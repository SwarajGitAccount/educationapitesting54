package com.education.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tutor_info",uniqueConstraints ={ @UniqueConstraint(columnNames = {"email"}),
                                               @UniqueConstraint(columnNames = {"contact_info"})})
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "full_name",nullable = false)
    private String name;
    @Column(name="email",nullable = false)
    private String emailId;
    @Column(name="contact_info",nullable = false)
    private String phoneNo;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "instructor" )
    private List<Student> students=new ArrayList<>();
}
