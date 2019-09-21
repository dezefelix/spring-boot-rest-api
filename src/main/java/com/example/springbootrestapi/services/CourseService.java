package com.example.springbootrestapi.services;

import com.example.springbootrestapi.models.Course;
import com.example.springbootrestapi.repositories.CourseRepository;
import com.example.springbootrestapi.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Using JPA with a mix of JPA OO style operations and Spring Boot's Crud Repository.
 * Trying out several ways to retrieve/save/update/delete data.
 */
@Service
public class CourseService {
    private CourseRepository courseRepo;
    private StudentRepository studentRepo;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepo = courseRepository;
    }

    // TODO: Far from optimal. The more entities, the more SELECT queries are send to the database.
    public List<Course> fetch() {
        List<Course> list = new ArrayList<>();
        this.courseRepo.findAll().forEach(list::add);
        return list;
    }

    public Course fetch(int id) {
        Optional<Course> course = this.courseRepo.findById(id);
        return course.orElse(null);
    }

    // TODO: Saving students to course does not work yet.
    public Course save(Course course) {
        this.courseRepo.save(course);
        return course;
    }

    // TODO: Updating course with students does not work yet
    public Course update(Course course) {
        Optional<Course> optionalCourse = this.courseRepo.findById(course.getId());
        if (optionalCourse.isPresent()) {

            Course c = optionalCourse.get();
            this.courseRepo.save(c);
            return c;
        }
        return null;
    }

    public void delete(int id) {
        this.courseRepo.deleteById(id);
    }

    private void saveStudents(Course course) {
    }
}
