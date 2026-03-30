package com.example.Marathon.trainingplan;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "training_plans")
public class TrainingPlan {

    @Id
    @GeneratedValue
    private UUID id;

    // 🔗 relatie naar user
    @Column(name = "user_id")
    private UUID userId;

    // 🔗 relatie naar marathon
    @Column(name = "marathon_id")
    private UUID marathonId;

    private LocalDate startDate;
    private LocalDate endDate;

    private String status;

    // getters & setters

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public UUID getMarathonId() { return marathonId; }
    public void setMarathonId(UUID marathonId) { this.marathonId = marathonId; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    @Override
    public String toString() {
    return "TrainingPlan{" +
            "id=" + id +
            ", userId=" + userId +
            ", marathonId=" + marathonId +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            ", status='" + status + '\'' +
            '}';
}
}