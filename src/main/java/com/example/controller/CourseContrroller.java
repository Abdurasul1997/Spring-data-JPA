package com.example.controller;

import com.example.dto.CourseDTO;
import com.example.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseContrroller {
    @Autowired
    private CourseService courseService;

    @PostMapping("/course")
    public ResponseEntity<?> create(@RequestBody CourseDTO dto) {
        CourseDTO courseDTO = courseService.create(dto);
        return ResponseEntity.status(HttpStatus.OK).body(courseDTO);
    }

    @GetMapping("course/{id}")
    public ResponseEntity<?> getByid(@PathVariable("id") Integer id) {
        CourseDTO byId = courseService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(byId);
    }

    @GetMapping("/course/all")
    public ResponseEntity<?> getAll() {
        List<CourseDTO> all = courseService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }

    @PutMapping("/course/{id}")
    public ResponseEntity<?> update(@RequestBody CourseDTO dto, @PathVariable("id") Integer id) {
        CourseDTO update = courseService.update(dto, id);
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }
    @DeleteMapping("/course/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Integer id){
        Boolean delete = courseService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(delete);


    }

}
