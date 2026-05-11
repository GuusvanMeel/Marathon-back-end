package com.example.RunningApp.Notification;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    private final NotificationService notificationService;

    public ScheduledTask(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Scheduled(cron = "0 0 8 * * *")
    public void runDaily() {

        notificationService.sendTodayTrainingReminders();
    }


}