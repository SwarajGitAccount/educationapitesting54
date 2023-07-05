package com.education.payload;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class InstructorDto {

    private long id;
    @NotNull
    @Size(min = 5,message = "name must consist of 5 letters")
    private String name;
    @NotEmpty
    @Email(message ="emailId must be in a valid emailId-format")
    private String emailId;
    @NotEmpty
    @Pattern(regexp="(^$|[0-9]{10})",message = "Phone no is must a valid phone no")
    private String phoneNo;
}
