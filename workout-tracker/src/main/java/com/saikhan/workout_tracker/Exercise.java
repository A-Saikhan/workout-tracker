package com.saikhan.workout_tracker;

import jakarta.persistence.*;

import java.util.List;
@Entity
public class Exercise {

    @Id
    @GeneratedValue
    private Long id;
    private String exerciseId;
    private String name;
    private String force;
    private String level;
    private String mechanic;
    private String equipment;
    private String category;

    @ElementCollection
    private List<String> primaryMuscles;

    @ElementCollection
    private List<String> secondaryMuscles;

    @ElementCollection
    @Column(length = 2000)
    private List<String> instructions;

    protected Exercise() {}

    public Exercise(String exerciseId, String name, String force, String level,
                    String mechanic, String equipment, String category,
                    List<String> primaryMuscles, List<String> secondaryMuscles,
                    List<String> instructions) {
        this.exerciseId = exerciseId;
        this.name = name;
        this.force = force;
        this.level = level;
        this.mechanic = mechanic;
        this.equipment = equipment;
        this.category = category;
        this.primaryMuscles = primaryMuscles;
        this.secondaryMuscles = secondaryMuscles;
        this.instructions = instructions;
    }

    public Long getId() {
        return id;
    }

    public String getExerciseId() {
        return exerciseId;
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    public String getForce() {
        return force;
    }

    public String getMechanic() {
        return mechanic;
    }

    public String getEquipment() {
        return equipment;
    }

    public String getCategory() {
        return category;
    }

    public List<String> getPrimaryMuscles() {
        return primaryMuscles;
    }

    public List<String> getSecondaryMuscles() {
        return secondaryMuscles;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    @Override
    public String toString() {
        return "Exercises{" +
                "id=" + id +
                ", exerciseId='" + exerciseId + '\'' +
                ", name='" + name + '\'' +
                ", force='" + force + '\'' +
                ", level='" + level + '\'' +
                ", mechanic='" + mechanic + '\'' +
                ", equipment='" + equipment + '\'' +
                ", category='" + category + '\'' +
                ", primaryMuscles=" + primaryMuscles +
                ", secondaryMuscles=" + secondaryMuscles +
                ", instructions=" + instructions +
                '}';
    }
}
