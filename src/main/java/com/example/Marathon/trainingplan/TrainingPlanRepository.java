package com.example.Marathon.trainingplan;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TrainingPlanRepository extends JpaRepository<TrainingPlan, UUID> {
}