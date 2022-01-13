package com.example.final_project;

import com.example.final_project.controller.StudentController;
import com.example.final_project.entity.Student;
import com.example.final_project.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.time.Month.APRIL;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StudentController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService service;

//    @MockBean
//    private StudentController studentController;

    @Test
    void whenGetStudentsExpectJsonResult() throws Exception {
        Student ema = new Student(
                "Ema",
                "ema@gmail.com",
                LocalDate.of(2000, APRIL, 15),
                "Senior");

        List<Student> allStudents = Arrays.asList(ema);

        given(service.getStudents()).willReturn(allStudents);

        mockMvc.perform(get("/api/student")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(ema.getName())))
                .andExpect(jsonPath("$[0].email", is(ema.getEmail())))
                .andExpect(jsonPath("$[0].year", is(ema.getYear())));

        verify(service, VerificationModeFactory.times(1)).getStudents();
        reset(service);

    }

    @Test
    void deleteNotFoundTest() throws Exception {

        mockMvc.perform(delete("/api/student/123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

//    @Test
//    void deleteStudentTest() throws Exception {
//
//        ResponseEntity responseEntity = new ResponseEntity(5, HttpStatus.valueOf(200));
//        when(studentController.deleteStudent(5)).thenReturn(responseEntity);
//
//        mockMvc.perform(delete("/api/student/5")
//                        .contentType(MediaType.APPLICATION_JSON))
//                        .andExpect(status().isOk());
//    }
}


