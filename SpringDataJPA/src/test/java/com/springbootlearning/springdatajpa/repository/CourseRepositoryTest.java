package com.springbootlearning.springdatajpa.repository;

import com.springbootlearning.springdatajpa.entity.Course;
import com.springbootlearning.springdatajpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class CourseRepositoryTest {
    private final CourseRepository repository;

    @Autowired
    CourseRepositoryTest(CourseRepository repository) {
        this.repository = repository;
    }

    @Test
    public void printCourses(){
        List<Course> courses = repository.findAll();
        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Arslan")
                .lastName("Ahmad")
                .build();

        Course course = Course.builder()
                .title("Python")
                .credit(4)
                .teacher(teacher)
                .build();

        repository.save(course);
    }
}