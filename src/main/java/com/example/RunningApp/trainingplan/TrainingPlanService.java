package com.example.RunningApp.trainingplan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RunningApp.marathon.MarathonRepository;
import com.example.RunningApp.trainingitem.TrainingItemService;
import com.example.RunningApp.trainingplan.dto.TrainingPlanListDTO;

@Service
public class TrainingPlanService {

    @Autowired
    TrainingPlanRepository repo;
    @Autowired
    MarathonRepository Mrepo;
    @Autowired
    TrainingItemService ItemService;



   
   public List<TrainingPlanListDTO> getAll() {
    List<TrainingPlan> plans =  repo.findAll();
    List<TrainingPlanListDTO> dtos = new ArrayList<>();
    for (TrainingPlan p : plans) {
        TrainingPlanListDTO dto = new TrainingPlanListDTO();

        dto.setId(p.getId().toString());
        dto.setMarathonName(Mrepo.getReferenceById(p.getMarathonId()).getName());
        dto.setEndDate(p.getEndDate().toString());
        dto.setStartDate(p.getStartDate().toString());
        dto.setStatus(p.getStatus());
        dtos.add(dto);
    }
    return dtos;
    


}
    public void CreatePlan(TrainingPlanInputForm data){
        
        TrainingPlan plan = new TrainingPlan();
        plan.setMarathonId(data.getMarathonId());
        plan.setStartDate(LocalDate.now());
        plan.setStatus("ONGOING");
        plan.setEndDate(Mrepo.getReferenceById(data.getMarathonId()).getDate()); //haalt de datum van de marathon repo op.
        plan.setUserId(UUID.fromString("8d769977-b8a1-420b-a198-a6863c14eb9c"));

        repo.save(plan);

        ItemService.generateItems(plan, data);

    }
    
}