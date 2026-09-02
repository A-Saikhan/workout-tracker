package com.saikhan.workout_tracker.controller;

import com.saikhan.workout_tracker.model.Exercise;
import com.saikhan.workout_tracker.repository.ExerciseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exercises")

public class ExerciseController {

    private final ExerciseRepository exerciseRepository;


    public ExerciseController(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @GetMapping

    public Page<Exercise> getAll(@PageableDefault(size = 20, sort = "name") Pageable pageable) {
        return exerciseRepository.findAll(pageable);
    }

    @GetMapping("/{exerciseId}")
    public Exercise getByExerciseId(@PathVariable String exerciseId) {
        return exerciseRepository.findByExerciseId(exerciseId).orElseThrow();
    }

    @PostMapping
    public Exercise create(@RequestBody Exercise exercise) {
        return exerciseRepository.save(exercise);
    }
}
