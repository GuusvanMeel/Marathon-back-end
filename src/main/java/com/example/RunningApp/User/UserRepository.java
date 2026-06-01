package com.example.RunningApp.User;

import com.example.RunningApp.marathon.Marathon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}
