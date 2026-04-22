package com.example.RunningApp.trainingplan;


import java.util.List;
import java.util.UUID;
import java.time.LocalTime;
import java.time.DayOfWeek;

public class TrainingPlanInputForm {

    private int fitnessLevel;
    private UUID marathonId;
    private LocalTime targetTime;
    private int trainingsPerWeek;
    private List<DayOfWeek> availableDays;

    // getters & setters

    public int getFitnessLevel() {
        return fitnessLevel;
    }

    public void setFitnessLevel(int fitnessLevel) {
        this.fitnessLevel = fitnessLevel;
    }

    public UUID getMarathonId() {
        return marathonId;
    }

    public void setMarathonId(UUID marathonId) {
        this.marathonId = marathonId;
    }

    public LocalTime getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(LocalTime targetTime) {
        this.targetTime = targetTime;
    }

    public int getTrainingsPerWeek() {
        return trainingsPerWeek;
    }

    public void setTrainingsPerWeek(int trainingsPerWeek) {
        this.trainingsPerWeek = trainingsPerWeek;
    }

    public List<DayOfWeek> getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(List<DayOfWeek> availableDays) {
        this.availableDays = availableDays;
    }

    
}
