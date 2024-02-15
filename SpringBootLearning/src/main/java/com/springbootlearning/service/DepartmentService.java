package com.springbootlearning.service;

import com.springbootlearning.entity.Department;

import java.util.List;

public interface DepartmentService {
    Department save(Department department);

    List<Department> findAll();

    Department findOne(Long id);

    void delete(Long id);

    Department fullUpdate(Long id, Department department);

    Department partialUpdate(Long id, Department department);

    Department findbyName(String name);
}
