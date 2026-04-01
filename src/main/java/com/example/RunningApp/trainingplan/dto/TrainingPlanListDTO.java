package com.example.RunningApp.trainingplan.dto;

public class TrainingPlanListDTO {
    private String id;

    private String marathonName;

    private String startDate;
    private String endDate;

    private String status;

      public String getId() {
        return id;
    }

    public String getMarathonName() {
        return marathonName;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return status;
    }

    // 🔹 Setters

    public void setId(String id) {
        this.id = id;
    }

    public void setMarathonName(String marathonName) {
        this.marathonName = marathonName;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
