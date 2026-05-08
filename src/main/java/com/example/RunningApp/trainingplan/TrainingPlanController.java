package com.example.RunningApp.trainingplan;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.RunningApp.trainingplan.dto.TrainingPlanListDTO;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/trainingplans")
public class TrainingPlanController {

    private final TrainingPlanService service;

    public TrainingPlanController(TrainingPlanService service) {
        this.service = service;
    }

    @GetMapping // GET
    public List<TrainingPlanListDTO> getAll() {
        return service.getAll();
    }
    @PostMapping
    public ResponseEntity<Void> createTrainingPlan(@RequestBody TrainingPlanInputForm dto) {

        String GREEN = "\u001B[32m";
        String CYAN = "\u001B[36m";
        String RESET = "\u001B[0m";

        System.out.println(CYAN + "---- NEW TRAINING PLAN ----" + RESET);

        System.out.println(GREEN + "Fitness Level: " + RESET + dto.getFitnessLevel());
        System.out.println(GREEN + "Marathon ID: " + RESET + dto.getMarathonId());
        System.out.println(GREEN + "Target Time: " + RESET + dto.getTargetTime());
        System.out.println(GREEN + "Trainings Per Week: " + RESET + dto.getTrainingsPerWeek());
        System.out.println(GREEN + "Available Days: " + RESET + dto.getAvailableDays());

        System.out.println(CYAN + "---------------------------" + RESET);

        service.CreatePlan(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();


    }
    @ExceptionHandler(jakarta.persistence.EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNotFound() {}

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleBadRequest() {}

}