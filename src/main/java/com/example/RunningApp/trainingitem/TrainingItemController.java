package com.example.RunningApp.trainingitem;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.RunningApp.trainingitem.dto.TrainingItemListDTO;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

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
    private final Collection<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamSseEvents() {

        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);

        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitter.onError(throwable -> {
            emitters.remove(emitter);
            emitter.completeWithError(throwable);
        });

        emitters.add(emitter);

        return emitter;
    }
    @GetMapping("/test-notification")
    public ResponseEntity<String> sendTestNotification() {
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event()
                        .name("training-notification")
                        .data("Test notificatie vanuit Spring Boot"));
            } catch (IOException e) {
                emitters.remove(emitter);
            }
        }

        return ResponseEntity.ok("Notification sent");
    }
}
