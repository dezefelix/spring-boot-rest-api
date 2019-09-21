package com.example.springbootrestapi.repositories;

import com.example.springbootrestapi.models.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository  extends CrudRepository<Course, Integer> {

}
