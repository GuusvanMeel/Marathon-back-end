package com.example.RunningApp.trainingplan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.RunningApp.Security.SecurityUtils;
import com.example.RunningApp.marathon.Marathon;
import org.springframework.stereotype.Service;
import com.example.RunningApp.marathon.MarathonRepository;
import com.example.RunningApp.trainingitem.TrainingItemService;
import com.example.RunningApp.trainingplan.dto.TrainingPlanListDTO;

@Service
public class TrainingPlanService {

    private final TrainingPlanRepository repo;
    private final MarathonRepository mRepo;
    private final TrainingItemService itemService;
    private final SecurityUtils securityUtils;

    public TrainingPlanService(TrainingPlanRepository repo,
                               MarathonRepository mRepo,
                               TrainingItemService itemService,
                               SecurityUtils securityUtils) {
        this.repo = repo;
        this.mRepo = mRepo;
        this.itemService = itemService;
        this.securityUtils = securityUtils;
    }

    public List<TrainingPlanListDTO> getAll() {
        UUID currentUserId = securityUtils.getCurrentUserId();
        List<TrainingPlan> plans = repo.findByUserId(currentUserId); // alleen van ingelogde user

        List<TrainingPlanListDTO> dtos = new ArrayList<>();
        for (TrainingPlan p : plans) {
            Marathon marathon = mRepo.findById(p.getMarathonId())
                    .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Marathon not found"));

            TrainingPlanListDTO dto = new TrainingPlanListDTO();
            dto.setId(p.getId().toString());
            dto.setMarathonName(marathon.getName());
            dto.setStartDate(p.getStartDate().toString());
            dto.setEndDate(p.getEndDate().toString());
            dto.setStatus(p.getStatus());
            dtos.add(dto);
        }
        return dtos;
    }

    public void createPlan(TrainingPlanInputForm data) {
        UUID currentUserId = securityUtils.getCurrentUserId();

        TrainingPlan plan = new TrainingPlan();
        plan.setMarathonId(data.getMarathonId());
        plan.setStartDate(LocalDate.now());
        plan.setStatus("ONGOING");
        plan.setEndDate(mRepo.getReferenceById(data.getMarathonId()).getDate());
        plan.setUserId(currentUserId); // was hardcoded UUID, nu dynamisch
        repo.save(plan);
        itemService.generateItems(plan, data);
    }
}