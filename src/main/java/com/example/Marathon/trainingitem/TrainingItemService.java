package com.example.Marathon.trainingitem;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingItemService {
    
    @Autowired
    TrainingItemRepository repo;

    public List<TrainingItem> GetAll() {
    return repo.findAll();
    }
    
    public List<TrainingItem> getByTrainingPlanID(UUID id){
        return repo.findByTrainingPlanId(id);
    }
}
