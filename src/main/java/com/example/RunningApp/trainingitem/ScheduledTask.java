package com.example.RunningApp.trainingitem;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ScheduledTask {

    private final TrainingItemController trainingItemController;

    public ScheduledTask(TrainingItemController trainingItemController) {
        this.trainingItemController = trainingItemController;
    }

    @Scheduled(fixedRate = 1000)
    public void run() {

        String notificationMessage =
                "Training reminder - " + LocalDateTime.now();

        trainingItemController.sendNotification(notificationMessage);
    }
}