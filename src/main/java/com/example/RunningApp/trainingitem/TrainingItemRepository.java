package com.example.RunningApp.trainingitem;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingItemRepository extends JpaRepository<TrainingItem, UUID> {
    
    List<TrainingItem> findByTrainingPlanId(UUID trainingPlanId);
    List<TrainingItem> findByDateBetween(LocalDateTime start, LocalDateTime end);
    List<TrainingItem> findByDate(LocalDateTime today);
}
