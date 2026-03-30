package com.example.RunningApp.trainingplan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TrainingPlanService {

    @Autowired
    TrainingPlanRepository repo;

   
   public List<TrainingPlan> getAll() {
    return repo.findAll();
}
}