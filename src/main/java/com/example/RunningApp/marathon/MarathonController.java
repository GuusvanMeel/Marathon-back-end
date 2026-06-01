package com.example.RunningApp.marathon;

import org.hibernate.event.spi.EventSource;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/marathons")
@CrossOrigin(origins = "http://localhost:5173")
public class MarathonController {

    private final MarathonRepository marathonRepository;

    public MarathonController(MarathonRepository marathonRepository) {
        this.marathonRepository = marathonRepository;
    }

    @GetMapping
    public List<Marathon> getAllMarathons() {
        return marathonRepository.findAll();
    }

}