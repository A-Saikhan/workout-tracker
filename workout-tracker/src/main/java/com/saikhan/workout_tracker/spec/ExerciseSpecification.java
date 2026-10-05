package com.saikhan.workout_tracker.spec;

import com.saikhan.workout_tracker.model.Exercise;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ExerciseSpecification {

    private ExerciseSpecification() {}

    public static Specification<Exercise> nameLike(String name) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Exercise> hasForce(String force) {
        return (root, query, cb) -> cb.equal(root.get("forces"), force);
    }

    public static Specification<Exercise> hasEquipment(String equipment) {
        return (root, query, cb) -> cb.equal(root.get("equipment"), equipment);
    }

    public static Specification<Exercise> hasCategory(String category) {
        return (root, query, cb) -> cb.equal(root.get("categories"), category);
    }

    public static Specification<Exercise> inPrimaryMuscles(List<String> primaryMuscles) {
        return (root, query, cb) -> {
            query.distinct(true);
            return root.join("primaryMuscles").in(primaryMuscles);
        };
    }

}
