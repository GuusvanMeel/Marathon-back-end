package com.example.RunningApp.Notification;

import com.example.RunningApp.trainingitem.TrainingItem;
import com.example.RunningApp.trainingitem.TrainingItemService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class NotificationService {

    private final TrainingItemService trainingItemService;

    public NotificationService(TrainingItemService service) {
        this.trainingItemService = service;
    }

    private final Collection<SseEmitter> emitters = new CopyOnWriteArrayList<>();


    public SseEmitter createEmitter() {

        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);

        emitter.onCompletion(() -> {
            System.out.println("Emitter completed");
            emitters.remove(emitter);
        });

        emitter.onTimeout(() -> {
            System.out.println("Emitter timed out");
            emitters.remove(emitter);
        });

        emitter.onError(throwable -> {
            System.out.println("Emitter error: " + throwable.getMessage());
            emitters.remove(emitter);
            emitter.completeWithError(throwable);
        });

        emitters.add(emitter);
        sendTodayTrainingRemindersToEmitter(emitter);
        return emitter;
    }

    public void sendTodayTrainingReminders() {
        List<TrainingItem> todayItems = trainingItemService.getTodayTrainingItems();
        for (TrainingItem item : todayItems) {
            String message = "Je hebt vandaag een training van "
                    + item.getTargetDistance()
                    + " km";

            BroadcastNotification(message);
            break; //omdat er tijdens testen meerdere trainingen per dag zijn, dus dan krijg je veel meer notifications dan verwacht.
        }
    }

    public void sendTodayTrainingRemindersToEmitter(SseEmitter emitter) {
        List<TrainingItem> todayItems = trainingItemService.getTodayTrainingItems();

        for (TrainingItem item : todayItems) {
            String message = "Je hebt vandaag een training van "
                    + item.getTargetDistance()
                    + " km";

            sendNotification(emitter, message);
        }
    }

    public void BroadcastNotification(String message) {
        System.out.println("Broadcasting to " + emitters.size() + " emitters: " + message);

        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event()
                        .name("training-notification")
                        .data(message));

                System.out.println("Send successful");
            } catch (IOException e) {
                System.out.println("Send failed: " + e.getMessage());
                emitters.remove(emitter);
            }
        }
    }

    public void sendNotification(SseEmitter emitter, String message) { //for 1 persoon, on startup
        try {
            emitter.send(SseEmitter.event()
                    .name("training-notification")
                    .data(message));
        } catch (IOException e) {
            emitters.remove(emitter);
        }
    }
}
