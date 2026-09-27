package com.saikhan.workout_tracker.dto;

import com.saikhan.workout_tracker.model.Exercise;

import java.util.List;

public record ExerciseSummary(
        String id,
        String name,
        List<String> primaryMuscles,
        String image
) {
    public static ExerciseSummary from(Exercise exercise) {
        return new ExerciseSummary(
                exercise.getExerciseId(),
                exercise.getName(),
                exercise.getPrimaryMuscles(),
                exercise.getImages().stream().findFirst().orElse(null)
        );
    }
}
