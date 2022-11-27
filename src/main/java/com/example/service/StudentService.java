package com.example.service;

import com.example.dto.StudentDTO;
import com.example.entity.StudentEntity;
import com.example.enums.GenderType;
import com.example.exp.ItemNotfoundException;
import com.example.exp.StudentCreationExeption;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentDTO create(StudentDTO dto) {
        if (dto.getAge() < 18) {
            throw new StudentCreationExeption("Age required");
        }
        if (dto.getName().length() < 3) {
            throw new StudentCreationExeption("name required");
        }
        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setLevel(dto.getLevel());
        entity.setGender(dto.getGender());
        entity.setLocalDateTime(LocalDateTime.now());

        studentRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<StudentDTO> createAll(List<StudentDTO> dtoList) {
        List<StudentEntity> entities = new LinkedList<>();
        for (StudentDTO dto : dtoList) {
            entities.add(toEntity(dto));
        }

        Iterable<StudentEntity> s = studentRepository.saveAll(entities);

        List<StudentDTO> dtoList1 = new LinkedList<>();
        for (StudentEntity entity : s) {
            dtoList1.add(toDO2(entity));
        }
        return dtoList1;

    }

    public List<StudentDTO> getAll() {
        List<StudentDTO> dtoList = new ArrayList<>();
        Iterable<StudentEntity> all = studentRepository.findAll();
        for (StudentEntity studentEntity : all) {
            dtoList.add(toDO(studentEntity));
        }
        return dtoList;
    }


    public StudentDTO update(StudentDTO dto, Integer id) {
        Optional<StudentEntity> entity = studentRepository.findById(id);
        if (entity.isEmpty()) {
            throw new ItemNotfoundException("Student not found");
        }

        if (dto.getName().trim().length() < 3) {
            throw new StudentCreationExeption("Name required");
        }
        if (dto.getAge() < 16 || dto.getAge() > 55) {
            throw new StudentCreationExeption("Age required");
        }
        StudentEntity entity1 = entity.get();
        entity1.setName(dto.getName());
        entity1.setAge(dto.getAge());
        entity1.setLevel(dto.getLevel());
        entity1.setLevel(dto.getLevel());
        studentRepository.save(entity1);
        dto.setId(entity1.getId());
        return dto;

    }


    public boolean delete(Integer id) {
        Optional<StudentEntity> byId = studentRepository.findById(id);
        if (byId.isEmpty()) {
            throw new ItemNotfoundException("Student not found");
        }
        studentRepository.deleteById(id);
        return true;

    }

    public StudentDTO getField(String name, String level, Integer age, GenderType type) {
        Optional<StudentEntity> byNameAndLevelAndAgeAndGender = studentRepository.findByNameAndLevelAndAgeAndGender(name, level, age, type);
        if (byNameAndLevelAndAgeAndGender.isEmpty()) {
            throw new ItemNotfoundException("Student not found");
        }
        StudentEntity entity = byNameAndLevelAndAgeAndGender.get();
        StudentDTO dto = toDO(entity);
        return dto;
    }

//    public List<StudentDTO> getDateList(LocalDateTime toDate,LocalDateTime fromDate) {
//        List<StudentEntity> entityList = studentRepository.findByLocalDateTimeBetween( toDate,  fromDate);
//
//        List<StudentDTO>studentDTOS=new LinkedList<>();
//        entityList.forEach(entity ->studentDTOS.add(toDO(entity)));
//        return studentDTOS;
//    }


    public StudentDTO toDO(StudentEntity entity) {
        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setAge(entity.getAge());
        dto.setLevel(entity.getLevel());
        dto.setGender(entity.getGender());
        dto.setName(entity.getName());
        dto.setLocalDateTime(entity.getLocalDateTime());
        return dto;
    }

    public StudentDTO toDO2(StudentEntity entity) {
        StudentDTO dto = new StudentDTO();
        dto.setAge(entity.getAge());
        dto.setLevel(entity.getLevel());
        dto.setGender(entity.getGender());
        dto.setName(entity.getName());
        dto.setLocalDateTime(entity.getLocalDateTime());
        return dto;
    }

    public StudentEntity toEntity(StudentDTO dto) {
        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        entity.setLocalDateTime(dto.getLocalDateTime());
        entity.setAge(dto.getAge());
        entity.setLevel(dto.getLevel());
        entity.setGender(dto.getGender());
        return entity;
    }
}
