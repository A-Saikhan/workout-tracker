package com.saikhan.workout_tracker.service;

import com.saikhan.workout_tracker.dto.ExerciseSummary;
import com.saikhan.workout_tracker.model.Exercise;
import com.saikhan.workout_tracker.repository.ExerciseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.saikhan.workout_tracker.spec.ExerciseSpecification.*;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Transactional(readOnly=true)
    public Page<ExerciseSummary> getExercises(String name, String force, String equipment, String category, List<String> primaryMuscles, Pageable pageable) {
        Specification<Exercise> filters = Specification
                .where(!StringUtils.hasText(name) ? Specification.unrestricted() : nameLike(name))
                .and(!StringUtils.hasText(force) ? Specification.unrestricted() : hasForce(force))
                .and(!StringUtils.hasText(equipment) ? Specification.unrestricted() : hasEquipment(equipment))
                .and(!StringUtils.hasText(category) ? Specification.unrestricted() : hasCategory(category))
                .and(CollectionUtils.isEmpty(primaryMuscles) ? Specification.unrestricted() : inPrimaryMuscles(primaryMuscles));

        return exerciseRepository.findAll(filters, pageable).map(ExerciseSummary::from);
    }


}
