package com.example.RunningApp.trainingitem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduledTask {

    private final TrainingItemController trainingItemController;
    @Autowired
    TrainingItemRepository repo;

    public ScheduledTask(TrainingItemController trainingItemController) {
        this.trainingItemController = trainingItemController;
    }

    @Scheduled(fixedRate = 10000)
    public void run() {

        String notificationMessage =
                "Training reminder - " + LocalDateTime.now();

        trainingItemController.sendNotification(notificationMessage);
    }
    @Scheduled(cron =  "0 0 8 * * *")
    public void Run() {
        checkTrainingItems();
    }
    public void checkTrainingItems() {

        List<TrainingItem> todayItems =
                repo.findByDate(LocalDate.now().atStartOfDay());

        for (TrainingItem item : todayItems) {

            String message =
                    "Je hebt vandaag een training van "
                            + item.getTargetDistance()
                            + " km";

            trainingItemController.sendNotification(message);
        }
    }
    @GetMapping("/test-scheduled-check")
    public ResponseEntity<String> testScheduledCheck() {

        checkTrainingItems();

        return ResponseEntity.ok("Scheduled check executed");
    }
    }

