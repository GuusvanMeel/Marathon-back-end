package com.example.Marathon.trainingitem;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingItemRepository extends JpaRepository<TrainingItem, UUID> {
    
    List<TrainingItem> findByTrainingPlanId(UUID trainingPlanId);
}
