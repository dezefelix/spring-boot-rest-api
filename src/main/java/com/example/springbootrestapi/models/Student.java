package com.example.springbootrestapi.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id @GeneratedValue
    private int id;
    private String name;

    @ManyToMany // Default fetch type for ManyToMany is LAZY. As is for OneToMany. Others are EAGER.
    private List<Course> courses = new ArrayList<>();

    public Student() { }

    public Student(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", courses=" + courses +
                '}';
    }
}
