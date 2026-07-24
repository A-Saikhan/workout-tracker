package com.saikhan.workout_tracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ExerciseSet {

    @Id
    @GeneratedValue
    private Long id;

    private double weight;
    private int reps;

    protected ExerciseSet() {}

    public ExerciseSet(int reps, double weight) {
        this.reps = reps;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public Long getId() {
        return id;
    }

    public int getReps() {
        return reps;
    }
}
