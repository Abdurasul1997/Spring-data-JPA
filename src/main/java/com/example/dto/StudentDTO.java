package com.example.dto;

import com.example.enums.GenderType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {
    private Integer id;

    private String name;
    private Integer age;
    private GenderType gender;
    private String level;
    private LocalDateTime localDateTime;
}
