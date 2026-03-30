package com.example.RunningApp.trainingitem;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingItemService {
    
    @Autowired
    TrainingItemRepository repo;

    public List<TrainingItem> getAll() {
    return repo.findAll();
    }
    
    public List<TrainingItem> getByTrainingPlanID(UUID id){
        return repo.findByTrainingPlanId(id);
    }
}
