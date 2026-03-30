package com.example.Marathon.trainingplan;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/trainingplans")
public class TrainingPlanController {

    private final TrainingPlanService service;

    public TrainingPlanController(TrainingPlanService service) {
        this.service = service;
    }
    @GetMapping("/test")
    public List<TrainingPlan> test(){
        return service.GetAll();
    }

    
}