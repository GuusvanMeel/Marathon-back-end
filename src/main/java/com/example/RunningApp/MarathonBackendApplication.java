package com.example.RunningApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MarathonBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarathonBackendApplication.class, args);
	}

}
