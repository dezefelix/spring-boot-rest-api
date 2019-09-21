package com.example.springbootrestapi.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id @GeneratedValue
    private int id;
    private String name;

    @ManyToMany(mappedBy = "courses")  // Default fetch type for ManyToMany is LAZY. As is for OneToMany. Others are EAGER.
    private List<Student> students = new ArrayList<>();

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }
}
