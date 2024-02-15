package com.springbootlearning.springdatajpa.repository;

import com.springbootlearning.springdatajpa.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    
}
