package com.example.RunningApp.trainingitem;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RunningApp.trainingitem.dto.TrainingItemListDTO;
import com.example.RunningApp.trainingplan.TrainingPlan;
import com.example.RunningApp.trainingplan.TrainingPlanInputForm;


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
            dto.setActualTime(formatDuration(trainingItem.getActualTime()) != null
                            ? trainingItem.getActualTime().toString()
                            : "-");
            dto.setDate(trainingItem.getDate().toString());
            dto.setStatus(trainingItem.getStatus());
            dto.setTargetDistance(trainingItem.getTargetDistance());
            dto.setTargetTime(formatDuration(trainingItem.getTargetTime()));
            dto.setID(trainingItem.getId());


            dtos.add(dto);
        }
        return dtos;
    }
    
    public void generateItems(TrainingPlan plan, TrainingPlanInputForm data){
        
        long weeks = ChronoUnit.WEEKS.between(plan.getStartDate(),plan.getEndDate());
        System.out.println("Weeks: " + weeks);
        List<TrainingItem> items = new ArrayList<>();
        for (int week = 0; week < weeks; week++) {
    for (int i = 0; i < data.getTrainingsPerWeek(); i++) {

        TrainingItem item = new TrainingItem();

        item.setTrainingPlanId(plan.getId());
        item.setDate(plan.getStartDate().plusWeeks(week).plusDays(i));
        item.setTargetDistance(5.0); // tijdelijk
        item.setStatus("PLANNED");
        item.setTargetTime(Duration.ofHours(1));
        items.add(item);
    }
}
        repo.saveAll(items);
    }
    private String formatDuration(Duration d) {
    if (d == null) return null;

    long hours = d.toHours();
    long minutes = d.toMinutesPart();

    return String.format("%d:%02d", hours, minutes);
}
}
