package com.example.controller;

import com.example.dto.StudentDTO;
import com.example.entity.StudentEntity;
import com.example.enums.GenderType;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    public ResponseEntity<?> create(@RequestBody StudentDTO dto) {
        StudentDTO result = studentService.create(dto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/student/all")
    public ResponseEntity<?> createAll(@RequestBody List<StudentDTO> dtoList) {
        List<StudentDTO> all = studentService.createAll(dtoList);
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/student/all")
    public List<StudentDTO> getAll() {
        List<StudentDTO> all = studentService.getAll();
        return all;
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<?> update(@RequestBody StudentDTO dto, @PathVariable("id") Integer id) {
        StudentDTO update = studentService.update(dto, id);
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        boolean delete = studentService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(delete);
    }

    @GetMapping("/student")
    public ResponseEntity<?> getField(String name, String level, Integer age, GenderType type) {
        StudentDTO field = studentService.getField(name, level, age, type);
        return ResponseEntity.status(HttpStatus.OK).body(field);
    }

//    @GetMapping("/student")
//    public ResponseEntity<?> getFindDateList(LocalDateTime toDate,LocalDateTime fromDate){
//        List<StudentDTO> dateList = studentService.getDateList(toDate,fromDate);
//        return ResponseEntity.status(HttpStatus.OK).body(dateList);
//    }


}
