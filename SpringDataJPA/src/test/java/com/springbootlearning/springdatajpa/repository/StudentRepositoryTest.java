package com.springbootlearning.springdatajpa.repository;

import com.springbootlearning.springdatajpa.entity.Guardian;
import com.springbootlearning.springdatajpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureDataJpa
class StudentRepositoryTest {

    private final StudentRepository studentRepository;

    @Autowired
    StudentRepositoryTest(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Test
    public void testThatSaveStudentWorks() {
        Student student = Student.builder()
                .firstName("Azan")
                .lastName("Rashid")
                .emailId("azanrashid26@gmail.com")
                //.guardianName("Rashid")
                //.guardianEmail("rashidmehmood19@gmail.com")
                //.guardianMobile("123-456789")
                .build();

        Student savedStudent = studentRepository.save(student);
        assertEquals(savedStudent, student);
    }

    @Test
    public void testThatStudentIsCreatedWithGuardian(){
        Guardian guardian = Guardian.builder()
                .name("Rashid")
                .email("rashidmehmood19@gmail.com")
                .mobile("123-456789")
                .build();

        Student student = Student.builder()
                .firstName("Azan")
                .lastName("Rashid")
                .emailId("azanrashid26@gmail.com")
                .guardian(guardian)
                .build();

        Student savedStudent = studentRepository.save(student);
        assertEquals(savedStudent, student);
    }

    @Test
    public void testThatStudentNameGetsUpdatedUsingEmailAddress(){
        studentRepository.updateStudentNameByEmailId(
                "Updated",
                "azanrashid26@gmail.com"
        );
    }
}