package com.example.entity;

import com.example.enums.GenderType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String level;
    @Column
    private Integer age;
    @Column()
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    @Column(name = "created_date")
    private LocalDateTime localDateTime;
}
