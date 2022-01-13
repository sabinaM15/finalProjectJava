package com.example.final_project.config;

import com.example.final_project.entity.Course;
import com.example.final_project.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CourseConfig {

    @Bean
    CommandLineRunner commandLineRunner(CourseRepository repository) {
        return args -> {
            Course  dataScience= new Course(
                    10L,
                    "Data Science");

            Course geography  = new Course(
                    30L,
                    "Geography ");

            repository.saveAll(
                    List.of(dataScience, geography));
        };
    }
}
