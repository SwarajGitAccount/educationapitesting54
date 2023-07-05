package com.education.service;

import com.education.payload.InstructorDto;
import com.education.payload.StudentDto;

import java.util.List;

public interface StudentService {

    StudentDto insertOneStudentByInstructorId(long instructorId,StudentDto dto);
    List<StudentDto> getAllStudent();
    StudentDto updateOneStudentByInstructorId(long instructorId,long studentId, StudentDto studentDto);
    void deleteOneStudentByInstructorId(long studentId,long instructorId);

}
