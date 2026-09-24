package com.saikhan.workout_tracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class ExerciseSet {

    @Id
    @GeneratedValue
    private Long id;

    @Column(precision = 6, scale = 2)
    private BigDecimal weight;
    private int reps;

    protected ExerciseSet() {}

    public ExerciseSet(int reps, BigDecimal weight) {
        this.reps = reps;
        this.weight = weight;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public Long getId() {
        return id;
    }

    public int getReps() {
        return reps;
    }
}
