package com.example.RunningApp.marathon;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MarathonRepository extends JpaRepository<Marathon, UUID> {

    
} 
