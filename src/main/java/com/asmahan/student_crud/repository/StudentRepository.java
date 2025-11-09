package com.asmahan.student_crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asmahan.student_crud.domain.Student;

public interface StudentRepository extends JpaRepository<Student,Long>{
    Optional<Student> findByEmail(String email);
    boolean existsByEmail(String email);    
} 

