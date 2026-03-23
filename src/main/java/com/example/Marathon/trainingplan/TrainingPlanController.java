package com.example.Marathon.trainingplan;

import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/trainingplans")
public class TrainingPlanController {

    private final TrainingPlanService service;

    public TrainingPlanController(TrainingPlanService service) {
        this.service = service;
    }

    @GetMapping
    public List<TrainingPlan> getPlans() {
                System.out.println("GET /trainingplans called");
        return service.getPlans();
    }

    @GetMapping("/user/{userId}")
    public List<TrainingPlan> getPlansForUser(@PathVariable Long userId) {
        return service.getPlansForUser(userId);
    }
}