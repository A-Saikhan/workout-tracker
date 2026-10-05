package com.saikhan.workout_tracker.repository;

import com.saikhan.workout_tracker.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Long>, JpaSpecificationExecutor<Exercise> {
    Optional<Exercise> findByExerciseId(String exerciseId);

    @Query("SELECT DISTINCT e.force FROM Exercise e WHERE e.force IS NOT NULL ORDER BY e.force")
    List<String> findDistinctForces();

    @Query("SELECT DISTINCT e.equipment FROM Exercise e WHERE e.equipment IS NOT NULL ORDER BY e.equipment")
    List<String> findDistinctEquipment();

    @Query("SELECT DISTINCT e.category FROM Exercise e ORDER BY e.category")
    List<String> findDistinctCategories();

    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT DISTINCT m FROM Exercise e JOIN e.primaryMuscles m WHERE m IS NOT NULL ORDER BY m")
    List<String> findDistinctPrimaryMuscles();
}
