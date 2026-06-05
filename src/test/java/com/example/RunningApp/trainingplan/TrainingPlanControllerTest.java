package com.example.RunningApp.trainingplan;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import jakarta.persistence.EntityNotFoundException;

@WebMvcTest(TrainingPlanController.class)
public class TrainingPlanControllerTest {
    

    @Autowired
    private MockMvc mockMvc;

   

    @MockitoBean
    private TrainingPlanService trainingPlanService;


    @Test
    @WithMockUser
    void getAll_returnsOk() throws Exception {

    when(trainingPlanService.getAll())
            .thenReturn(List.of());

    mockMvc.perform(get("/trainingplans"))
            .andExpect(status().isOk());
        }
    @Test
    @WithMockUser
    void getAll_returnsNotFound() throws Exception {

        when(trainingPlanService.getAll())
                .thenThrow(new EntityNotFoundException());

        mockMvc.perform(get("/trainingplans"))
                .andExpect(status().isNotFound());
    }
        @Test
        @WithMockUser
        void createTrainingPlan_returnsCreated() throws Exception {

            String json = """
                {
                "fitnessLevel": 3,
                "marathonId": "123e4567-e89b-12d3-a456-426614174000",
                "targetTime": "04:00:00",
                "trainingsPerWeek": 4,
                "availableDays": ["MONDAY", "WEDNESDAY"]
                }
                """;

            mockMvc.perform(post("/trainingplans")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                    .andExpect(status().isCreated());

            verify(trainingPlanService).createPlan(any(TrainingPlanInputForm.class));
        }
                @Test
        @WithMockUser
        void createTrainingPlan_invalidJson_returnsBadRequest() throws Exception {

            String invalidJson = """
                {
                "fitnessLevel": 3,
                "marathonId": "geen uuid lol"
                }
                """;

            mockMvc.perform(post("/trainingplans")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(invalidJson))
                    .andExpect(status().isBadRequest());

            verify(trainingPlanService, never()).createPlan(any());
        }
}
