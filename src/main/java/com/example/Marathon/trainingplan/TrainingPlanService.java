package com.example.Marathon.trainingplan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TrainingPlanService {

    @Autowired
    TrainingPlanRepository repo;

   
   public List<TrainingPlan> GetAll() {
    return repo.findAll();
}
}