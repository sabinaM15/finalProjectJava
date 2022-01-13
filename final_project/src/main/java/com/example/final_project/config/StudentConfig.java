package com.example.final_project.config;

import com.example.final_project.entity.Student;
import com.example.final_project.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.APRIL;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunnerStudent(StudentRepository repository) {
        return args -> {
            Student maria = new Student(
                    "Maria",
                    "maria@gmail.com",
                    LocalDate.of(2000, APRIL, 15),
                    "Junior");

            Student andrei = new Student(
                    "Andrei",
                    "andrei@gmail.com",
                    LocalDate.of(2004, APRIL, 15),
                    "Senior");

            repository.saveAll(
                    List.of(maria, andrei));
        };
    }
}