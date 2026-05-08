package com.example.RunningApp.trainingitem;

import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

public class ScheduledTask {
    @Scheduled(fixedRate = 1000)
    public void run(){
        String notificationMessage =
                "Training reminder - " + LocalDateTime.now();

        TrainingItemController.sendNotification(notificationMessage);
    }
}
