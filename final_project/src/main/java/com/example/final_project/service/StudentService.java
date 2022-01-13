package com.example.final_project.service;

import com.example.final_project.entity.Student;
import com.example.final_project.entity.Teacher;
import com.example.final_project.entity.Year;
import com.example.final_project.repository.StudentRepository;
import com.example.final_project.repository.YearRepository;
import org.apache.catalina.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final YearRepository yearRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, YearRepository yearRepository) {

        this.studentRepository = studentRepository;
        this.yearRepository = yearRepository;
    }

    public List<Student> getStudents()
    {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }

        Integer studentAge = student.getAge();

        if (studentAge < 14) {
            Year yearJ = new Year(student.getStudent_id(), "Junior");
            yearRepository.save(yearJ);
            student.setYear("Junior");
        } else {
            Year yearS = new Year(student.getStudent_id(), "Senior");
            yearRepository.save(yearS);
            student.setYear("Senior");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Integer studentId)
    {
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Integer studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exists"));

        if (name != null && name.length() > 0) {
            student.setName(name);
        }

        if (email != null && email.length() > 0) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }

    public boolean existsById(Integer id) {

        return studentRepository.existsById(id);
    }
}

