package com.saikhan.workout_tracker.repository;

import com.saikhan.workout_tracker.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {


}
