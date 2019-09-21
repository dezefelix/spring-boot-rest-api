package com.example.springbootrestapi.repositories;

import com.example.springbootrestapi.models.Course;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Course, Integer> {

}
