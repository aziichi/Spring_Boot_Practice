package com.springbootlearning.springdatajpa.repository;

import com.springbootlearning.springdatajpa.entity.Course;
import com.springbootlearning.springdatajpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TeacherRepositoryTest {
    private final TeacherRepository repository;

    @Autowired
    TeacherRepositoryTest(TeacherRepository repository) {
        this.repository = repository;
    }

    @Test
    public void testThatSaveTeacherWorks(){
        Course courseDSA = Course.builder()
                .title("DSA")
                .credit(4)
                .build();

        Course courseJava = Course.builder()
                .title("Java")
                .credit(3)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("Ali")
                .lastName("Ahmad")
                //.courses(List.of(courseDSA, courseJava))
                .build();

        repository.save(teacher);
    }
}