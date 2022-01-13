package com.example.final_project;

import com.example.final_project.controller.TeacherController;
import com.example.final_project.entity.Teacher;
import com.example.final_project.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.time.Month.APRIL;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TeacherController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
class TeacherControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherService service;

    @Test
    void whenGetTeachersExpectJsonResult() throws Exception {
        Teacher ema = new Teacher(
                "Ema",
                "ema@gmail.com");

        List<Teacher> allTeachers = Arrays.asList(ema);

        given(service.getTeachers()).willReturn(allTeachers);

        mockMvc.perform(get("/api/teacher")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(ema.getname())))
                .andExpect(jsonPath("$[0].email", is(ema.getemail())));

        verify(service, VerificationModeFactory.times(1)).getTeachers();
        reset(service);

    }

    @Test
    void deleteNotFoundTest() throws Exception {

        mockMvc.perform(delete("/api/teacher/123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
