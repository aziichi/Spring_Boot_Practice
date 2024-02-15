package com.springbootlearning.springdatajpa.repository;

import com.springbootlearning.springdatajpa.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "update tbl_student set first_name = :name where email_address = :emailId",
            nativeQuery = true
    )
    @Modifying
    @Transactional
    int updateStudentNameByEmailId(
            @Param("name") String name,
            @Param("emailId") String emailId);
}
