package com.springbootlearning.service.impl;

import com.springbootlearning.entity.Department;
import com.springbootlearning.repository.DepartmentRepository;
import com.springbootlearning.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findOne(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Department fullUpdate(Long id, Department department) {
        department.setDepartmentId(id);
        return departmentRepository.save(department);
    }

    @Override
    public Department partialUpdate(Long id, Department department) {
        Department foundDepartment = departmentRepository.findById(id).orElse(null);
        if(foundDepartment == null){
            return null;
        }
        else{
            if(Objects.nonNull(department.getDepartmentName()) &&
                    !"".equalsIgnoreCase(department.getDepartmentName())){
                foundDepartment.setDepartmentName(department.getDepartmentName());
            }

            if(Objects.nonNull(department.getDepartmentAddress()) &&
                    !"".equalsIgnoreCase(department.getDepartmentAddress())){
                foundDepartment.setDepartmentAddress(department.getDepartmentAddress());
            }

            if(Objects.nonNull(department.getDepartmentCode()) &&
                    !"".equalsIgnoreCase(department.getDepartmentCode())){
                foundDepartment.setDepartmentCode(department.getDepartmentCode());
            }
            return departmentRepository.save(foundDepartment);
        }
    }

    @Override
    public Department findbyName(String name) {
//        return departmentRepository.findByDepartmentName(name);
        return departmentRepository.findByDepartmentNameIgnoreCase(name);
    }
}
