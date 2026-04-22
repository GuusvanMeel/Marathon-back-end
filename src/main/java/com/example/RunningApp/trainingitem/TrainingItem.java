package com.example.RunningApp.trainingitem;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Duration;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;



@Entity
@Table(name = "training_items")
public class TrainingItem {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "training_plan_id")
    private UUID trainingPlanId;

    private LocalDate date;

    @Column(name = "target_distance")
    
    private Double targetDistance;

    @Column(name = "actual_distance")
   
    private Double actualDistance;

    @Column(name = "target_time")
    @JdbcTypeCode(SqlTypes.INTERVAL_SECOND)
    private Duration targetTime;

    @Column(name = "actual_time")
     @JdbcTypeCode(SqlTypes.INTERVAL_SECOND)
    private Duration actualTime;

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

    public Duration getTargetTime() { return targetTime; }
    public void setTargetTime(Duration targetTime) { this.targetTime = targetTime; }

    public Duration getActualTime() { return actualTime; }
    public void setActualTime(Duration actualTime) { this.actualTime = actualTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}