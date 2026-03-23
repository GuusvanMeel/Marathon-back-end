package com.example.Marathon.trainingplan;

import java.util.List;

import org.springframework.stereotype.Service;
@Service
public class TrainingPlanService {

    public List<TrainingPlan> getPlans() {
        return List.of(
            createPlan(1L, 1L, 4),
            createPlan(2L, 1L, 6),
            createPlan(3L, 2L, 8)
        );
    }

    public List<TrainingPlan> getPlansForUser(Long userId) {
        return getPlans().stream()
                .filter(plan -> plan.getUserId().equals(userId))
                .toList();
    }

    private TrainingPlan createPlan(Long id, Long userId, int weeks) {
        TrainingPlan plan = new TrainingPlan();
        plan.setId(id);
        plan.setUserId(userId);
        plan.setWeeks(weeks);
        return plan;
    }
}