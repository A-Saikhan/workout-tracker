package com.saikhan.workout_tracker.controller;

import com.saikhan.workout_tracker.model.Exercise;
import com.saikhan.workout_tracker.model.Workout;
import com.saikhan.workout_tracker.model.WorkoutExercise;
import com.saikhan.workout_tracker.repository.ExerciseRepository;
import com.saikhan.workout_tracker.repository.WorkoutRepository;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import java.util.List;

@RestController
@RequestMapping ("/api/workouts")
public class WorkoutController {
    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;

    public WorkoutController(WorkoutRepository workoutRepository, ExerciseRepository exerciseRepository) {
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @GetMapping
    public List<Workout> getAll() {
        return workoutRepository.findAll();
    }

    @GetMapping("/{id}")
    public Workout getById(@PathVariable Long id) {
        return workoutRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public Workout create(@RequestBody Workout workout) {
        for (WorkoutExercise we : workout.getExercises()) {
            String exerciseId = we.getExercise().getExerciseId();
            Exercise exercise = exerciseRepository.findByExerciseId(exerciseId).orElseThrow();
            we.setExercise(exercise);
        }
        return workoutRepository.save(workout);
    }
}
