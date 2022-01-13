package com.example.final_project.service;

import com.example.final_project.entity.School;
import com.example.final_project.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public List<School> getSchools() {
        return schoolRepository.findAll();
    }

    public void addNewSchool(School school) {
        Optional<School> schoolOptional = schoolRepository.findSchoolById(school.getId());
        if (schoolOptional.isPresent()){
            throw new IllegalStateException("id taken");
        }
        schoolRepository.save(school);
    }

    public void deleteSchool(Long schoolId) {
        schoolRepository.deleteById(schoolId);
    }

    public boolean existsById(Long id) {
        return schoolRepository.existsById(id);
    }
}
