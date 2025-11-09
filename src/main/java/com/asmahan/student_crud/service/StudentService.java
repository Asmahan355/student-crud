package com.asmahan.student_crud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.asmahan.student_crud.domain.Student;
import com.asmahan.student_crud.repository.StudentRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentService {
    private final StudentRepository repo;
    public StudentService(StudentRepository repo){
        this.repo=repo;
    }

    public Student create(Student s){
        if(repo.existsByEmail(s.getEmail()))
        {
            throw new IllegalArgumentException("Email already usedd");
        }
        return repo.save(s);
    }

    @Transactional(readOnly = true)
    public List<Student> findAll(){
        return repo.findAll();

    }
    @Transactional(readOnly = true)
    public Student findOne(Long id){
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found"));
    }
    public Student update(Long id, Student in) {
        Student existing = findOne(id);
        if (!existing.getEmail().equals(in.getEmail()) && repo.existsByEmail(in.getEmail())) {
            throw new IllegalArgumentException("Email already used");
        }
        existing.setFullName(in.getFullName());
        existing.setEmail(in.getEmail());
        return existing; // flush auto via JPA
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("Student not found");
        repo.deleteById(id);
    }


}
