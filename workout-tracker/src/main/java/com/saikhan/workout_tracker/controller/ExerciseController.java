package com.saikhan.workout_tracker.controller;

import com.saikhan.workout_tracker.dto.ExerciseSummary;
import com.saikhan.workout_tracker.model.Exercise;
import com.saikhan.workout_tracker.repository.ExerciseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    private final ExerciseRepository exerciseRepository;


    public ExerciseController(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Transactional(readOnly = true)
    @GetMapping
    public Page<ExerciseSummary> getAll(
            @RequestParam(required = false) String name,
            @PageableDefault(size = 20, sort = "name") Pageable pageable) {
        return ((!StringUtils.hasText(name))
                ? exerciseRepository.findAll(pageable)
                : exerciseRepository.findByNameContainingIgnoreCase(name, pageable))
                .map(ExerciseSummary::from);
    }

    @Transactional(readOnly = true)
    @GetMapping("/{exerciseId}")
    public Exercise getByExerciseId(@PathVariable String exerciseId) {
        return exerciseRepository.findByExerciseId(exerciseId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise not found"));
    }

}
