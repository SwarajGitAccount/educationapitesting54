package com.education.serviceImpl;


import com.education.entity.Instructor;
import com.education.entity.Student;
import com.education.exception.ResourceNotFoundException;
import com.education.payload.StudentDto;
import com.education.repository.InstructorRepository;
import com.education.repository.StudentRepository;
import com.education.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepo;
    @Autowired
    private InstructorRepository instructorRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public StudentDto insertOneStudentByInstructorId(long instructorId, StudentDto dto) {
        Instructor instructor = instructorRepo.findById(instructorId).orElseThrow(() -> new ResourceNotFoundException("I'd not found :" + instructorId));
        Student student = this.mapToEntity(dto);
        student.setInstructor(instructor);
        Student savedStudent = studentRepo.save(student);
        StudentDto studentDto = this.mapToDto(savedStudent);
        return studentDto;
    }

    @Override
    public List<StudentDto> getAllStudent() {
        List<Student> allStudent = studentRepo.findAll();
        List<StudentDto> collect = allStudent.stream().map((x) -> this.mapToDto(x)).collect(Collectors.toList());

        return collect;
    }

    @Override
    public StudentDto updateOneStudentByInstructorId(long instructorId, long studentId, StudentDto studentDto) {
        Instructor instructor = instructorRepo.findById(instructorId).orElseThrow(() -> new ResourceNotFoundException("I'd not found :" + instructorId));
        Student student = studentRepo.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("I'd not found :" + studentId));

        student.setName(studentDto.getName());
        student.setCourseName(studentDto.getCourseName());
        student.setEmailId(studentDto.getEmailId());

        student.setInstructor(instructor);
        Student updateStudent = studentRepo.save(student);

        StudentDto studentDto1 = this.mapToDto(updateStudent);

        return studentDto1;
    }

    @Override
    public void deleteOneStudentByInstructorId(long studentId, long instructorId) {
        Instructor instructor = instructorRepo.findById(instructorId).orElseThrow(() -> new ResourceNotFoundException("I'd not found :" + instructorId));
        Student student = studentRepo.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("I'd not found :" + studentId));
        student.setInstructor(instructor);
        studentRepo.delete(student);
    }

    private Student mapToEntity(StudentDto dto){
        Student student=this.modelMapper.map(dto,Student.class);
        return student;
    }
    public StudentDto mapToDto(Student student){
        StudentDto dto=this.modelMapper.map(student,StudentDto.class);
        return dto;
    }



}
