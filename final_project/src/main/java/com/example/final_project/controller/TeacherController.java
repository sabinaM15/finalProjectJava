package com.example.final_project.controller;

import com.example.final_project.entity.Teacher;
import com.example.final_project.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<Teacher> getTeachers(){
        return teacherService.getTeachers();
    }

    @PostMapping
    public void registerNewTeacher(@RequestBody Teacher teacher){
        teacherService.addNewTeacher(teacher);
    }

    @DeleteMapping(path = "{teacherId}")
    public ResponseEntity<Long> deleteTeacher(@PathVariable("teacherId") Long id){

        boolean teacherExist = teacherService.existsById(id);
        if (!teacherExist) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        teacherService.deleteTeacher(id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping(path = "{teacherId}")
    public void updateTeacher(@PathVariable("teacherId") Long teacherId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email){
        teacherService.updateTeacher(teacherId, name, email);
    }
}
