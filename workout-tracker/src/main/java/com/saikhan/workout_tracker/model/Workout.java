package com.saikhan.workout_tracker.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Workout {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime date;

    @OneToMany(cascade = CascadeType.ALL)
    private List<WorkoutExercise> exercises;

    protected Workout() {}

    public Workout(LocalDateTime date, List<WorkoutExercise> exercises) {
        this.date = date;
        this.exercises = exercises;
    }

    public List<WorkoutExercise> getExercises() {
        return exercises;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Long getId() {
        return id;
    }

    public int getNumberOfExercises() {
        return exercises != null ? exercises.size() : 0;
    }
}
