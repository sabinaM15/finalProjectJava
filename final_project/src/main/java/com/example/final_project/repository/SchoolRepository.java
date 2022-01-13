package com.example.final_project.repository;

import com.example.final_project.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository <School, Long> {

    @Query("SELECT sh FROM School sh WHERE sh.id = ?1")
    Optional<School> findSchoolById(Long id);
}
