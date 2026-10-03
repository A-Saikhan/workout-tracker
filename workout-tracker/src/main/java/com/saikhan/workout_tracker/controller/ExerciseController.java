package com.saikhan.workout_tracker.controller;

import com.saikhan.workout_tracker.dto.ExerciseSummary;
import com.saikhan.workout_tracker.model.Exercise;
import com.saikhan.workout_tracker.repository.ExerciseRepository;
import com.saikhan.workout_tracker.service.ExerciseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseService exerciseService;


    public ExerciseController(ExerciseRepository exerciseRepository, ExerciseService exerciseService) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseService = exerciseService;
    }

    @Transactional(readOnly = true)
    @GetMapping
    public Page<ExerciseSummary> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String force,
            @RequestParam(required = false) String equipment,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) List<String> primaryMuscles,
            @PageableDefault(size = 20, sort = "name") Pageable pageable) {
        return exerciseService.getExercises(name, force, equipment, category, primaryMuscles, pageable);
    }

    @Transactional(readOnly = true)
    @GetMapping("/{exerciseId}")
    public Exercise getByExerciseId(@PathVariable String exerciseId) {
        return exerciseRepository.findByExerciseId(exerciseId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise not found"));
    }

}
