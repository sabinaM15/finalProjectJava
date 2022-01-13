package com.example.final_project.controller;

import com.example.final_project.service.CourseService;
import com.example.final_project.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getCourses() {
        return courseService.getCourses();
    }

    @PostMapping
    public void registerNewCourse(@RequestBody Course course){
        courseService.addNewCourse(course);
    }

    @DeleteMapping(path = "{courseId}")
    public ResponseEntity<Long> deleteCourse(@PathVariable("courseId") Long id){

        boolean courseExist = courseService.existsById(id);
        if (!courseExist) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        courseService.deleteCourse(id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping(path = "{courseId}")
    public void updateCourse(@PathVariable("courseId") Long courseId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) Long course_id){
        courseService.updateCourse(courseId, name, course_id);
    }

}
