package com.example.Marathon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class Marathoncontroller {

    @GetMapping("/api/marathons")
    public List<Map<String, Object>> getMarathons() {
        return List.of(
                Map.of(
                        "id", 1,
                        "name", "TEST TEST",
                        "location", "Amsterdam",
                        "distance", 42.195
                ),
                Map.of(
                        "id", 2,
                        "name", "Rotterdam Marathon",
                        "location", "Rotterdam",
                        "distance", 42.195
                )
        );
    }
}