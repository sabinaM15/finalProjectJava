package com.example.final_project.controller;

import com.example.final_project.entity.School;
import com.example.final_project.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/school")
public class SchoolController {

    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping
    public List<School> getSchools() {
        return schoolService.getSchools();
    }

    @PostMapping
    public void registerNewSchool(@RequestBody School school){
        schoolService.addNewSchool(school);
    }

    @DeleteMapping(path = "{schoolId}")
    public ResponseEntity<Long> deleteSchool(@PathVariable("schoolId") Long id){

        boolean schoolExist = schoolService.existsById(id);
        if (!schoolExist) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        schoolService.deleteSchool(id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
