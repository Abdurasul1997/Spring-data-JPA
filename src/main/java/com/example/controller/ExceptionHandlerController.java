package com.example.controller;

import com.example.exp.CourseCreationExeption;
import com.example.exp.ItemNotfoundException;
import com.example.exp.StudentCreationExeption;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({ItemNotfoundException.class, StudentCreationExeption.class, CourseCreationExeption.class})
    public ResponseEntity<?> hendle(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
