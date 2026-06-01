package com.example.RunningApp.trainingplan;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TrainingPlanRepository extends JpaRepository<TrainingPlan, UUID> {
        List<TrainingPlan> findByUserId(UUID userId);
}