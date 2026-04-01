package com.example.RunningApp.trainingitem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RunningApp.trainingitem.dto.TrainingItemListDTO;

@Service
public class TrainingItemService {

    @Autowired
    TrainingItemRepository repo;
    

    public List<TrainingItem> getAll() {
        return repo.findAll();
    }

    public List<TrainingItemListDTO> getByTrainingPlanID(UUID id) {

        List<TrainingItemListDTO> dtos = new ArrayList<>();
        List<TrainingItem> items = repo.findByTrainingPlanId(id);
        if (items.isEmpty()) {
            return new ArrayList<>();
        }
        for (TrainingItem trainingItem : items) {
            TrainingItemListDTO dto = new TrainingItemListDTO();
            dto.setActualDistance(trainingItem.getActualDistance());
            dto.setActualTime( // hier moet nog logica komen om er goede string van te maken voor de frontend
                    trainingItem.getActualTime() != null
                            ? trainingItem.getActualTime().toString()
                            : "-");
            dto.setDate(trainingItem.getDate().toString());
            dto.setStatus(trainingItem.getStatus());
            dto.setTargetDistance(trainingItem.getTargetDistance());
            dto.setTargetTime(trainingItem.getTargetTime().toString());// IDEM


            dtos.add(dto);
        }
        return dtos;
    }
}
