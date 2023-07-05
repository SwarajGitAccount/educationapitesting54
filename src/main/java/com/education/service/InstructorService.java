package com.education.service;

import com.education.entity.Instructor;
import com.education.payload.InstructorDto;

import java.util.List;

public interface InstructorService {

    InstructorDto insertOneInstructor(InstructorDto dto);
    List<InstructorDto> getAllInstructors();
    InstructorDto updateOneInstructor(long id,InstructorDto dto);
    void deleteOneInstructor(long id);
     List<InstructorDto>  findInstructorByEmailId(String emailId);





}
