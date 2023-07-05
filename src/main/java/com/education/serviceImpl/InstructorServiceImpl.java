package com.education.serviceImpl;

import com.education.entity.Instructor;
import com.education.exception.ResourceNotFoundException;
import com.education.payload.InstructorDto;
import com.education.repository.InstructorRepository;
import com.education.service.InstructorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstructorServiceImpl implements InstructorService {
    @Autowired
    private InstructorRepository instructorRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public InstructorDto insertOneInstructor(InstructorDto dto) {
        Instructor instructor = this.mapToEntity(dto);
        Instructor savedInstructor = instructorRepo.save(instructor);
        InstructorDto instructorDto = this.mapToDto(savedInstructor);
        return instructorDto;
    }

    @Override
    public List<InstructorDto> getAllInstructors() {
        List<Instructor> allInstructors = instructorRepo.findAll();
        List<InstructorDto> collect = allInstructors.stream().map((x) -> this.mapToDto(x)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public InstructorDto updateOneInstructor(long id, InstructorDto dto) {
        Instructor instructor = instructorRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("I'd not found :" + id));
        instructor.setName(dto.getName());
        instructor.setEmailId(dto.getEmailId());
        instructor.setPhoneNo(dto.getPhoneNo());
        Instructor updatedInstructor = instructorRepo.save(instructor);
        InstructorDto instructorDto = this.mapToDto(updatedInstructor);
        return instructorDto;
    }

    @Override
    public void deleteOneInstructor(long id) {
        Instructor instructor = instructorRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("I'd not found :" + id));
        instructorRepo.delete(instructor);
    }

    @Override
    public  List<InstructorDto> findInstructorByEmailId(String emailId) {
         List<Instructor> byEmailId = instructorRepo.findByEmailId(emailId);
        List<InstructorDto> collect = byEmailId.stream().map((x) -> this.mapToDto(x)).collect(Collectors.toList());
        return collect;
    }

    private Instructor mapToEntity(InstructorDto instructorDto){
        Instructor instructor=this.modelMapper.map(instructorDto,Instructor.class);
        return instructor;
    }
    public InstructorDto mapToDto(Instructor instructor){
        InstructorDto instructorDto=this.modelMapper.map(instructor,InstructorDto.class);
        return instructorDto;
    }



}
