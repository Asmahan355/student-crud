package com.asmahan.student_crud.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asmahan.student_crud.domain.Student;
import com.asmahan.student_crud.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentController {
private final StudentService service;

public StudentController(StudentService service){
    this.service=service;

}
//Create 
@PostMapping
public Student create(@Valid @RequestBody Student s){
    return service.create(s);
}
@GetMapping
public List<Student> findAll(){
    return service.findAll();
}

@GetMapping("/{id}")
public Student findOne(@PathVariable Long id){
    return service.findOne(id);
}
@PutMapping("/{id}")
public Student update(@PathVariable Long id,@Valid @RequestBody Student student){
    return service.update(id, student);
}
@DeleteMapping("/{id}")
public void delete(@PathVariable Long id){
    service.delete(id);
}
}
