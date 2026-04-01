package com.example.RunningApp.trainingitem;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.example.RunningApp.trainingitem.dto.TrainingItemListDTO;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/trainingitems")
public class TrainingItemController {

    private final TrainingItemService service;

    public TrainingItemController(TrainingItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<TrainingItem> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}/item")
    public List<TrainingItemListDTO> getByTrainingPlanID(@PathVariable UUID id) {
        return service.getByTrainingPlanID(id);
                
    }
    
}
