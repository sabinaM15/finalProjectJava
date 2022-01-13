package com.example.final_project.repository;

import com.example.final_project.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CourseRepository extends JpaRepository <Course, Long> {

    @Query("SELECT c FROM Course c WHERE c.name = ?1")
    Optional<Course> findCourseByName(String name);
}
