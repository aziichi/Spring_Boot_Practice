package com.springbootlearning.service;

import com.springbootlearning.entity.Department;
import com.springbootlearning.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    private final DepartmentService departmentService;

    @MockBean
    private final DepartmentRepository departmentRepository;

    @Autowired
    DepartmentServiceTest(DepartmentService departmentService, DepartmentRepository departmentRepository) {
        this.departmentService = departmentService;
        this.departmentRepository = departmentRepository;
    }

    @BeforeEach
    void setUp() {
        Department dept = Department
                .builder()
                .departmentId(1L)
                .departmentName("CS")
                .departmentAddress("CUI Sahiwal")
                .departmentCode("CS-01")
                .build();

        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("CS"))
                .thenReturn(dept);
    }

    @Test
    public void testThatFindByDepartmentNameGivesReturnsDepartmentIfItExists(){
        String deptName = "CS";
        Department found = departmentService.findbyName(deptName);
        assertEquals(deptName, found.getDepartmentName());
    }
}