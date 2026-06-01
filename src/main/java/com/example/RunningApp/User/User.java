package com.example.RunningApp.User;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "fitness_level")
    private Integer fitnessLevel;

    @Column(name = "password")
    private String password;

    public User() {
    }

    public User(UUID id, String email, String name, Integer fitnessLevel, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.fitnessLevel = fitnessLevel;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFitnessLevel() {
        return fitnessLevel;
    }

    public void setFitnessLevel(Integer fitnessLevel) {
        this.fitnessLevel = fitnessLevel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}