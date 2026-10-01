package dev.langchain4j.spring.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DecisionTriageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testTriageEndpoint() throws Exception {
        mockMvc.perform(post("/triage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"text\":\"I need an enterprise invoice and sales agreement for 1000 users.\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modelName").value("devops-thiago/classone-gemma4-e2b"))
                .andExpect(jsonPath("$.department").isNotEmpty())
                .andExpect(jsonPath("$.urgentProbability").isNumber())
                .andExpect(jsonPath("$.frustrationScore").isNumber());
    }
}
