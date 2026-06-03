package com.example.RunningApp.trainingitem;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
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

    @GetMapping //GET
    public List<TrainingItem> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}/item") //GET{ID}
    public List<TrainingItemListDTO> getByTrainingPlanID(@PathVariable UUID id) {
        
        return service.getByTrainingPlanID(id);
                
    }
    @PostMapping("/test-create-today/{trainingPlanId}")
    public ResponseEntity<TrainingItem> createTestTrainingItemForToday(
            @PathVariable UUID trainingPlanId
    ) {

        TrainingItem item = new TrainingItem();

        item.setTrainingPlanId(trainingPlanId);
        item.setDate(LocalDate.now());

        item.setTargetDistance(5.0);
        item.setActualDistance(0.0);

        item.setTargetTime(Duration.ofMinutes(30));
        item.setActualTime(Duration.ZERO);

        item.setStatus("PLANNED");

        TrainingItem savedItem = service.Save(item);

        return ResponseEntity.ok(savedItem);
    }

}
