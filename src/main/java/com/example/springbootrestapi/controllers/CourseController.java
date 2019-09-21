package com.example.springbootrestapi.controllers;

import com.example.springbootrestapi.models.Course;
import com.example.springbootrestapi.services.CourseService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "courses")
class CourseController {
    private Gson g = new Gson();
    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getCourses() {
        return this.courseService.fetch();
    }

    @GetMapping("/{id}")
    public Course getCourse(@PathVariable int id) {
        return this.courseService.fetch(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Course createCourse(@RequestBody Course course) {
        return courseService.save(course);
    }

    @PutMapping
    public Course updateCourse(@RequestBody Course course) {
        return this.courseService.update(course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable int id) {
        this.courseService.delete(id);
    }
}
