package com.example;

import com.example.dto.CourseDTO;
import com.example.dto.StudentDTO;
import com.example.enums.GenderType;
import com.example.service.CourseService;
import com.example.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SpringDataJpaStudentCourseApplicationTests {
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;

    @Test
    void create() {
        StudentDTO dto = new StudentDTO();
        dto.setAge(27);
        dto.setName("joxa");
        dto.setLevel("1");
        dto.setGender(GenderType.valueOf("ERKAK"));
        StudentDTO dto1 = studentService.create(dto);
        System.out.println(dto1);
    }

    @Test
    void createAll() {
        List<StudentDTO> dtoList = new ArrayList<>();

        StudentDTO dto = new StudentDTO();
        dto.setAge(26);
        dto.setName("boxxi");
        dto.setLevel("2");
        dto.setGender(GenderType.valueOf("ERKAK"));
        dtoList.add(dto);

        StudentDTO dto1 = new StudentDTO();
        dto.setAge(24);
        dto.setName("meja");
        dto.setLevel("3");
        dto.setGender(GenderType.valueOf("ERKAK"));
        dtoList.add(dto1);

        StudentDTO dto2 = new StudentDTO();
        dto.setAge(26);
        dto.setName("suxa");
        dto.setLevel("4");
        dto.setGender(GenderType.valueOf("ERKAK"));
        dtoList.add(dto2);

        List<StudentDTO> all = studentService.createAll(dtoList);
        System.out.println(all);

    }

    @Test
    void getAll() {
        List<StudentDTO> all = studentService.getAll();
        for (StudentDTO dto : all) {
            System.out.println(dto);
        }
    }

    @Test
    void update() {
        StudentDTO dto = new StudentDTO();
        dto.setAge(33);
        dto.setName("aaaaaaaaaaaaa");
        dto.setLevel("33");
        dto.setGender(GenderType.valueOf("AYOL"));
        StudentDTO update = studentService.update(dto, 7);
        System.out.println(update);
    }

    @Test
    void delete() {
        boolean delete = studentService.delete(7);
        System.out.println(delete);
    }

    @Test
    void getField() {
        StudentDTO field = studentService.getField("suxa", "4", 26, GenderType.valueOf("ERKAK"));
        System.out.println(field);
    }

    /////////////////////////////////////////////////////////////////

    @Test
    void createC() {
        CourseDTO dto = new CourseDTO();
        dto.setName("fizika");
        dto.setPrice("300000");
        dto.setDuration(31);
        CourseDTO dto1 = courseService.create(dto);

        System.out.println(dto1);
    }

    @Test
    void getByIdC() {
        System.out.println(courseService.getById(1));
    }

    @Test
    void getAllC() {
        List<CourseDTO> all = courseService.getAll();
        all.forEach(dto -> System.out.println(dto));
    }

    @Test
    void updateC() {
        CourseDTO dto=new CourseDTO();
        dto.setName("englishhhh");
        dto.setDuration(15);
        dto.setPrice("150000");
        System.out.println(courseService.update(dto, 2));
    }


}
