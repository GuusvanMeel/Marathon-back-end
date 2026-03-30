package com.example.Marathon.trainingitem;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "training_items")
public class TrainingItem {

    @Id
    @GeneratedValue
    private UUID id;

    // 🔗 FK naar training_plan
    @Column(name = "training_plan_id")
    private UUID trainingPlanId;

    private LocalDate date;

    @Column(name = "target_distance")
    private Double targetDistance;

    @Column(name = "actual_distance")
    private Double actualDistance;

    @Column(name = "target_time")
    private LocalTime targetTime;

    @Column(name = "actual_time")
    private LocalTime actualTime;

    private String status;

    // 🔹 getters & setters

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getTrainingPlanId() { return trainingPlanId; }
    public void setTrainingPlanId(UUID trainingPlanId) { this.trainingPlanId = trainingPlanId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Double getTargetDistance() { return targetDistance; }
    public void setTargetDistance(Double targetDistance) { this.targetDistance = targetDistance; }

    public Double getActualDistance() { return actualDistance; }
    public void setActualDistance(Double actualDistance) { this.actualDistance = actualDistance; }

    public LocalTime getTargetTime() { return targetTime; }
    public void setTargetTime(LocalTime targetTime) { this.targetTime = targetTime; }

    public LocalTime getActualTime() { return actualTime; }
    public void setActualTime(LocalTime actualTime) { this.actualTime = actualTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}