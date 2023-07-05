package com.education.controller;

import com.education.payload.StudentDto;
import com.education.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/instructor/{instructorId}/students")
    public ResponseEntity<StudentDto> insertOneStudent(@PathVariable long instructorId, @RequestBody StudentDto dto){
        StudentDto studentDto = studentService.insertOneStudentByInstructorId(instructorId, dto);
        return new ResponseEntity<>(studentDto, HttpStatus.CREATED);
    }
    @GetMapping("/get")
    public ResponseEntity<List<StudentDto>> getAllStudents(){
        List<StudentDto> allStudent = studentService.getAllStudent();
        return new ResponseEntity<>(allStudent,HttpStatus.OK);
    }
    @PutMapping("/instructor/{studentId}/{instructorId}/students")
    public ResponseEntity<StudentDto> updateOneStudent(@PathVariable long studentId,@PathVariable long instructorId,@RequestBody StudentDto dto){
        StudentDto studentDto = studentService.updateOneStudentByInstructorId(studentId, instructorId, dto);
        return new ResponseEntity<>(studentDto,HttpStatus.OK);
    }
    @DeleteMapping("instructor/{instructorId}/{studentId}/student")
    public ResponseEntity<String> deleteOneStudent(@PathVariable long studentId,@PathVariable long instructorId){
        studentService.deleteOneStudentByInstructorId(studentId,instructorId);
        return new ResponseEntity<>("I'd has been deleted.",HttpStatus.OK);
    }

}
