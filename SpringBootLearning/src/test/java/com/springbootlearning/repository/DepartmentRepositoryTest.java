package com.springbootlearning.repository;

import com.springbootlearning.entity.Department;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryTest {

    private final DepartmentRepository departmentRepository;

    private final TestEntityManager testEntityManager;

    @Autowired
    DepartmentRepositoryTest(DepartmentRepository departmentRepository, TestEntityManager testEntityManager) {
        this.departmentRepository = departmentRepository;
        this.testEntityManager = testEntityManager;
    }

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("CS")
                .departmentAddress("CUI Sahiwal")
                .departmentCode("CS-06")
                .build();

        testEntityManager.persist(department);
    }

    @Test
    void findByDepartmentNameIgnoreCase() {
        Department found = departmentRepository.findByDepartmentNameIgnoreCase("CS");
        assertEquals(found.getDepartmentName(), "CS");
    }
}