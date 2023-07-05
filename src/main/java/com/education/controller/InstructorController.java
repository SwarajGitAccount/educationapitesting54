package com.education.controller;
import com.education.payload.InstructorDto;
import com.education.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/instructor")
public class InstructorController {


    @Autowired
    private InstructorService instructorService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/post")
    public ResponseEntity<?> insertOneInstructor(@Valid @RequestBody InstructorDto dto, BindingResult br){

       if(br.hasErrors()){
           return new ResponseEntity<>(br.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
       }
       InstructorDto instructorDto = instructorService.insertOneInstructor(dto);
        return new ResponseEntity<>(instructorDto, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<InstructorDto>> getAllInstructors(){
        List<InstructorDto> allInstructors = instructorService.getAllInstructors();
        return new ResponseEntity<>(allInstructors,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    public ResponseEntity<InstructorDto> updateOneInstructor(@PathVariable long id,@RequestBody InstructorDto dto){
        InstructorDto dto1=instructorService.updateOneInstructor(id,dto);
        return new ResponseEntity<>(dto1,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteOneInstructor(@PathVariable long id){
        instructorService.deleteOneInstructor(id);
        return new ResponseEntity<>("I'd deleted successfully.",HttpStatus.OK);
    }
    @GetMapping("/x/{emailId}")
    public ResponseEntity<List<InstructorDto>> findInstructorByEmailId(@PathVariable String emailId){
        List<InstructorDto> instructorByEmailId = instructorService.findInstructorByEmailId(emailId);
        return new ResponseEntity<>(instructorByEmailId,HttpStatus.OK);
    }



}
