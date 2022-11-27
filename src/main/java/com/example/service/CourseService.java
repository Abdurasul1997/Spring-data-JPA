package com.example.service;

import com.example.dto.CourseDTO;
import com.example.entity.CourseEntity;
import com.example.exp.CourseCreationExeption;
import com.example.exp.ItemNotfoundException;
import com.example.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public CourseDTO create(CourseDTO dto) {

        if (dto.getName().trim().length() < 3) {
            throw new CourseCreationExeption("Name requiared");
        }

        CourseEntity entity = new CourseEntity();

        entity.setName(dto.getName());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setDuration(dto.getDuration());
        entity.setPrice(dto.getPrice());

        courseRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public CourseDTO getById(Integer id) {
        Optional<CourseEntity> optional = courseRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotfoundException("Course not found");
        }
        CourseEntity entity = optional.get();
        CourseDTO dto = dto(entity);
        return dto;

    }




    public List<CourseDTO> getAll() {
        Iterable<CourseEntity> all = courseRepository.findAll();
        List<CourseDTO> dtoList=new ArrayList<>();

        for (CourseEntity entity : all) {
            dtoList.add(dto(entity));
        }
        return dtoList;
    }

    private CourseDTO dto(CourseEntity entity){
        CourseDTO dto = new CourseDTO();
        dto.setId(entity.getId());
        dto.setDuration(entity.getDuration());
        dto.setName(entity.getName());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    public CourseDTO update(CourseDTO dto, Integer id) {
        Optional<CourseEntity> byId = courseRepository.findById(id);
        if(byId.isEmpty()){
            throw new ItemNotfoundException("Course not found");
        }
        if(dto.getName().trim().length()<3){
            throw new CourseCreationExeption("Name required");
        }
        CourseEntity entity = byId.get();
        entity.setDuration(dto.getDuration());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setCreatedDate(LocalDateTime.now());

        courseRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public Boolean delete(Integer id) {
        Optional<CourseEntity> byId = courseRepository.findById(id);
        if(byId.isEmpty()){
            throw new ItemNotfoundException(" Course not found");
        }
        CourseEntity entity = byId.get();
        courseRepository.delete(entity);
        return true;
    }
}
