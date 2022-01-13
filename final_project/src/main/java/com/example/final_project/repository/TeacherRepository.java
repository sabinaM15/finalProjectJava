package com.example.final_project.repository;

import com.example.final_project.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository <Teacher, Long> {

    @Query("SELECT t FROM Teacher t WHERE t.email = ?1")
    Optional<Teacher> findTeacherByEmail(String email);
}
