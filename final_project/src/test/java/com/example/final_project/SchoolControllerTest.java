package com.example.final_project;

import com.example.final_project.controller.SchoolController;
import com.example.final_project.entity.School;
import com.example.final_project.service.SchoolService;
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

import java.util.Arrays;
import java.util.List;

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
@WebMvcTest(value = SchoolController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
class SchoolControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SchoolService service;

    @Test
    void whenGetSchoolsExpectJsonResult() throws Exception {
        School PrimarySchool = new School(1L);

        List<School> allSchools = Arrays.asList(PrimarySchool);

        given(service.getSchools()).willReturn(allSchools);

        mockMvc.perform(get("/api/school")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(PrimarySchool.getId().intValue())));

        verify(service, VerificationModeFactory.times(1)).getSchools();
        reset(service);
    }

    @Test
    void deleteNotFoundTest() throws Exception {

        mockMvc.perform(delete("/api/school/123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
