package com.example.repository;

import com.example.dto.StudentDTO;
import com.example.entity.StudentEntity;
import com.example.enums.GenderType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {
    Optional<StudentEntity> findByNameAndLevelAndAgeAndGender(String name, String level, Integer age, GenderType type);

//    List<StudentEntity> findByLocalDateTimeBetween(LocalDateTime toDate,LocalDateTime fromDate);
}
