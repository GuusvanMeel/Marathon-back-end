package com.example.Marathon.trainingitem;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/trainingitems")
public class TrainingItemController {

    private final TrainingItemService service;

    public TrainingItemController(TrainingItemService service) {
        this.service = service;
    }

    @GetMapping("/test")
    public List<TrainingItem> test() {
        return service.GetAll();
    }

    @GetMapping("/{id}/item")
    public List<TrainingItem> getByTrainingPlanID(@PathVariable UUID id) {
        return service.getByTrainingPlanID(id);
                
    }
}
