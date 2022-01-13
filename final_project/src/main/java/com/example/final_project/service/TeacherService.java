package com.example.final_project.service;

import com.example.final_project.entity.Teacher;
import com.example.final_project.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();

    }

    public void addNewTeacher(Teacher teacher) {
        Optional<Teacher> teacherOptional = teacherRepository.findTeacherByEmail(teacher.getemail());
        if (teacherOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }

        teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long teacherId) {
        teacherRepository.deleteById(teacherId);
    }

    @Transactional
    public void updateTeacher(Long teacherId, String name, String email) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new IllegalStateException("teacher with id " + teacherId + " does not exists"));

        if( name != null && name.length() > 0 ){
            System.out.println("name: " + teacher.getname().equals(name));
            teacher.setname(name);
        }

        if( email != null && email.length() > 0 ){
            Optional<Teacher> teacherOptional = teacherRepository.findTeacherByEmail(email);
            if(teacherOptional.isPresent()){
                throw new IllegalStateException("email taken");

            }
            System.out.println("email: " + teacher.getemail().equals(email));

            teacher.setemail(email);
        }
    }

    public boolean existsById(Long id) {

        return teacherRepository.existsById(id);
    }
}
