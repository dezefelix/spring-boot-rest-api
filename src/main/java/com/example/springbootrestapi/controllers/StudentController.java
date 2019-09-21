package com.example.springbootrestapi.controllers;

import com.example.springbootrestapi.models.Student;
import com.example.springbootrestapi.services.StudentService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "students")
class StudentController {
    private Gson g = new Gson();
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return this.studentService.fetch();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id) {
        return this.studentService.fetch(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody String json) {
        Student student = this.convertToStudent(json);
        return studentService.save(student);
    }

    @PutMapping
    public Student updateStudent(@RequestBody Student student) {
        return this.studentService.update(student);
    }

    @DeleteMapping
    public void deleteStudent(@RequestBody Student student) {
        this.studentService.delete(student);
    }

    private Student convertToStudent(String jsonString) {
        return this.g.fromJson(jsonString, Student.class);
    }
}
