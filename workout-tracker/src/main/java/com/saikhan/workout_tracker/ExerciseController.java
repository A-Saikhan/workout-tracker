package com.saikhan.workout_tracker;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    private final ExerciseRepository exerciseRepository;


    public ExerciseController(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @GetMapping
    public List<Exercise> getAll() {
        return exerciseRepository.findAll();
    }

    @GetMapping("/{id}")
    public Exercise getById(@PathVariable Long id) {
        return exerciseRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public Exercise create(@RequestBody Exercise exercise) {
        return exerciseRepository.save(exercise);
    }
}
