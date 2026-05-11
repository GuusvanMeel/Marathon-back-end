package com.example.RunningApp.trainingitem;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingItemRepository extends JpaRepository<TrainingItem, UUID> {
    
    List<TrainingItem> findByTrainingPlanId(UUID trainingPlanId);
    List<TrainingItem> findByDateBetween(LocalDate start, LocalDate end);
    List<TrainingItem> findByDate(LocalDate today);
}
