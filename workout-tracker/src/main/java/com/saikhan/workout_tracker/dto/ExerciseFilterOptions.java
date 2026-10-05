package com.saikhan.workout_tracker.dto;

import java.util.List;

public record ExerciseFilterOptions(
        List<String> forces,
        List<String> equipment,
        List<String> categories,
        List<String> primaryMuscles
) {

}
