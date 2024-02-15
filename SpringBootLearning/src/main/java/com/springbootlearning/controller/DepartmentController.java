package com.springbootlearning.controller;

import com.springbootlearning.entity.Department;
import com.springbootlearning.service.DepartmentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/departments")
    public ResponseEntity<Department> saveDepartment(@Valid @RequestBody Department department){
        LOGGER.info("saveDepartment got hit");
        return new ResponseEntity<>(departmentService.save(department), HttpStatus.CREATED);
    }

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getDepartmentList(){
        List<Department> departments = departmentService.findAll();
        if(departments.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(departments, HttpStatus.OK);
        }
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable("id") Long id){
        Department department = departmentService.findOne(id);
        if(department == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(department, HttpStatus.OK);
        }

    }

    @GetMapping("/departments/name/{name}")
    public ResponseEntity<Department> getDepartmentByName(@PathVariable("name") String name){
        Department department = departmentService.findbyName(name);
        if(department == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(department, HttpStatus.OK);
        }
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity deleteMapping(@PathVariable("id") Long id){
        Department department = departmentService.findOne(id);
        if(department == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        departmentService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<Department> fullUpdateDepartment(@PathVariable("id") Long id,
                                                           @RequestBody Department department){
        if(departmentService.findOne(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Department updatedDepartment = departmentService.fullUpdate(id, department);
        return new ResponseEntity<>(updatedDepartment,HttpStatus.OK);
    }

    @PatchMapping("/departments/{id}")
    public ResponseEntity<Department> partialUpdateDepartment(@PathVariable("id") Long id,
                                                           @RequestBody Department department){
        if(departmentService.findOne(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Department updatedDepartment = departmentService.partialUpdate(id, department);
        return new ResponseEntity<>(updatedDepartment,HttpStatus.OK);
    }

}
