package com.saikhan.workout_tracker.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class WorkoutExercise {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Exercise exercise;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ExerciseSet> sets;

    protected WorkoutExercise() {}

    public WorkoutExercise(List<ExerciseSet> sets, Exercise exercise) {
        this.sets = sets;
        this.exercise = exercise;
    }

    public Long getId() {
        return id;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public List<ExerciseSet> getSets() {
        return sets;
    }

    public void setExercise (Exercise exercise) {
        this.exercise = exercise;
    }
}
