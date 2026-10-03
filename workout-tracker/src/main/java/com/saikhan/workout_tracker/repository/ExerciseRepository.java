package com.saikhan.workout_tracker.repository;

import com.saikhan.workout_tracker.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Long>, JpaSpecificationExecutor<Exercise> {
    Optional<Exercise> findByExerciseId(String exerciseId);

}
