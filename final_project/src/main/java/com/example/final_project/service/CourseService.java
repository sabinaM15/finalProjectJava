package com.example.final_project.service;

import com.example.final_project.entity.Course;
import com.example.final_project.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {

        this.courseRepository = courseRepository;
    }

    public List<Course> getCourses() {
        return courseRepository.findAll();

    }

    public void addNewCourse(Course course) {
        Optional<Course> courseOptional = courseRepository.findCourseByName(course.getName());
        if (courseOptional.isPresent()){
            throw new IllegalStateException("name taken");
        }

        courseRepository.save(course);
    }

    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    @Transactional
    public void updateCourse(Long courseId, String name, Long teacher_id) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new IllegalStateException("course with id " + courseId + " does not exists"));

        if( name != null && name.length() > 0 ){
            course.setName(name);
        }

        if( name != null && name.length() > 0 ){
            Optional<Course> courseOptional = courseRepository.findCourseByName(name);
            if(courseOptional.isPresent()){
                throw new IllegalStateException("name taken");
            }

            course.setName(name);
        }
    }

    public boolean existsById(Long id) {

        return courseRepository.existsById(id);
    }


}
