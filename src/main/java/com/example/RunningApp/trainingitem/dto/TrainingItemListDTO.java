package com.example.RunningApp.trainingitem.dto;

import java.util.UUID;

public class TrainingItemListDTO {

    private UUID id;
    private String date;

    private Double targetDistance;
    private Double actualDistance;

    private String targetTime;
    private String actualTime;

    private String status;


    // 🔹 getters & setters

    public UUID GetId(){
        return id;
    }
    public void setID(UUID id){
        this.id = id;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getTargetDistance() {
        return targetDistance;
    }

    public void setTargetDistance(Double targetDistance) {
        this.targetDistance = targetDistance;
    }

    public Double getActualDistance() {
        return actualDistance;
    }

    public void setActualDistance(Double actualDistance) {
        this.actualDistance = actualDistance;
    }

    public String getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(String targetTime) {
        this.targetTime = targetTime;
    }

    public String getActualTime() {
        return actualTime;
    }

    public void setActualTime(String actualTime) {
        this.actualTime = actualTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}