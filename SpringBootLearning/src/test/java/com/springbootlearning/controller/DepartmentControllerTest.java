package com.springbootlearning.controller;

import com.springbootlearning.entity.Department;
import com.springbootlearning.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    private final MockMvc mockMvc;

    @MockBean
    private final DepartmentService departmentService;

    private Department department;

    @Autowired
    DepartmentControllerTest(MockMvc mockMvc, DepartmentService departmentService) {
        this.mockMvc = mockMvc;
        this.departmentService = departmentService;
    }


    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentId(1L)
                .departmentName("CS")
                .departmentAddress("CUI Sahiwal")
                .departmentCode("CS-06")
                .build();
    }

    @Test
    void saveDepartment() throws Exception {
        Department inputDepartment = Department.builder()
                .departmentName("CS")
                .departmentAddress("CUI Sahiwal")
                .departmentCode("CS-06")
                .build();

        Mockito.when(departmentService.save(inputDepartment))
                .thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                           "departmentName": "CS",
                           "departmentAddress": "COMSATS Sahiwal",
                           "departmentCode":  "CS-06"
                        }"""))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void getDepartment() throws Exception {
        Mockito.when(departmentService.findOne(1L))
                .thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.get("/departments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.departmentName").value("CS"));
    }
}