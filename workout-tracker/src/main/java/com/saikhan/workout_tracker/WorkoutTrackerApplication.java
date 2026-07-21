package com.saikhan.workout_tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WorkoutTrackerApplication {

	private static final Logger logger = LoggerFactory.getLogger(WorkoutTrackerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WorkoutTrackerApplication.class, args);
	}

}
