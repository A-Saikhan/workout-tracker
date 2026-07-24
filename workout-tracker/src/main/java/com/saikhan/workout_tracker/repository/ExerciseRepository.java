package com.saikhan.workout_tracker.repository;

import com.saikhan.workout_tracker.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Optional<Exercise> findByExerciseId(String exerciseId);
}
