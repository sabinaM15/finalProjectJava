package com.example.final_project.config;

import com.example.final_project.entity.Teacher;
import com.example.final_project.repository.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TeacherConfig {
    @Bean
    CommandLineRunner commandLineRunnerTeacher(TeacherRepository repository) {
        return args -> {
            Teacher andrei = new Teacher(
                    "Andrei",
                    "andrei@gmail.com");

            Teacher alexandra = new Teacher(
                    "Alexandra",
                    "alexandra@gmail.com");

            repository.saveAll(
                    List.of(andrei, alexandra)
            );
        };
    }
}
