package com.springbootlearning.springdatajpa.repository;

import com.springbootlearning.springdatajpa.entity.Course;
import com.springbootlearning.springdatajpa.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {
    private final CourseMaterialRepository repository;

    @Autowired
    CourseMaterialRepositoryTest(CourseMaterialRepository repository) {
        this.repository = repository;
    }

    @Test
    public void testThatCourseMaterialGetsSaved(){
        Course course = Course.builder()
                .title("DSA")
                .credit(4)
                .build();

        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.course.com")
                .course(course)
                .build();

        repository.save(courseMaterial);
    }
}