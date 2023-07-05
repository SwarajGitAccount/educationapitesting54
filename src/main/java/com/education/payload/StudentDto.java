package com.education.payload;

import lombok.Data;

@Data
public class StudentDto {
    private long id;
    private String name;
    private String courseName;
    private String emailId;
}
