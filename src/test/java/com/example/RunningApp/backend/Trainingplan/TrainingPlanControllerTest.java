package com.example.RunningApp.backend.Trainingplan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TrainingPlanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnAllTrainingPlans() throws Exception {
        mockMvc.perform(get("/trainingplans"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void shouldReturnTrainingPlansList() throws Exception {
        mockMvc.perform(get("/trainingplans"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"));
    }
    @Test
    void shouldCreateTrainingPlan() throws Exception {
        String json = """
        {
          "fitnessLevel": 2,
          "marathonId": "445c289a-7b6f-4ab2-aa31-5682c13fc7c7",
          "targetTime": "04:00",
          "trainingsPerWeek": 3,
          "availableDays": ["MONDAY", "WEDNESDAY"]
        }
        """;

        mockMvc.perform(post("/trainingplans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void shouldHandleNonExistingMarathonId() throws Exception {
        String json = """
    {
      "fitnessLevel": 1,
      "marathonId": "00000000-0000-0000-0000-000000000000",
      "targetTime": "04:00",
      "trainingsPerWeek": 3,
      "availableDays": ["MONDAY"]
    }
    """;

        mockMvc.perform(post("/trainingplans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().is5xxServerError()); // of later 4xx als je het fixt
    }
}
