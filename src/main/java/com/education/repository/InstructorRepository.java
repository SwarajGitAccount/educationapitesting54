package com.education.repository;

import com.education.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface InstructorRepository extends JpaRepository<Instructor,Long> {

         List<Instructor> findByEmailId(String emailId);

}
