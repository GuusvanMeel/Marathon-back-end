package com.example.RunningApp.trainingplan;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.RunningApp.trainingplan.dto.TrainingPlanListDTO;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/trainingplans")
public class TrainingPlanController {

    private final TrainingPlanService service;

    public TrainingPlanController(TrainingPlanService service) {
        this.service = service;
    }
    @GetMapping
    public List<TrainingPlanListDTO> getAll(){
        return service.getAll();
    }

    
}