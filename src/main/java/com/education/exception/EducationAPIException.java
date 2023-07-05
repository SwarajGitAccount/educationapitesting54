package com.education.exception;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class EducationAPIException extends RuntimeException {


    public EducationAPIException(HttpStatus httpStatus, String s) {


    }
}
