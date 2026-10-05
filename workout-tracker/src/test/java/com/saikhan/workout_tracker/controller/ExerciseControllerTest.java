package com.saikhan.workout_tracker.controller;


import com.saikhan.workout_tracker.dto.ExerciseFilterOptions;
import com.saikhan.workout_tracker.dto.ExerciseSummary;
import com.saikhan.workout_tracker.model.Exercise;
import com.saikhan.workout_tracker.repository.ExerciseRepository;
import com.saikhan.workout_tracker.service.ExerciseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExerciseController.class)
public class ExerciseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ExerciseRepository exerciseRepository;

    @MockitoBean
    private ExerciseService exerciseService;

    Exercise squat = new Exercise(
            "Barbell_Squat", "Barbell Squat", "push", "beginner", "compound",
            "barbell", "strength",
            List.of("quadriceps"), List.of("glutes", "hamstrings"),
            List.of("Stand with the bar on your traps."), List.of("Barbell_Squat/0.jpg")
    );

    Exercise bench = new Exercise(
            "Bench_Press", "Bench Press", "push", "beginner", "compound",
            "barbell", "strength",
            List.of("chest"), List.of("triceps", "shoulders"),
            List.of("Lie back on a flat bench."), List.of("Bench_Press/0.jpg")
    );

    Exercise plank = new Exercise(
            "Plank", "Plank", "static", "beginner", null,
            "body only", "strength",
            List.of("abdominals"), List.of(),
            List.of("Get into a push-up position."), List.of()
    );

    @Test
    void defaultExercisePage() throws Exception {
        when(exerciseService.getExercises(any(), any(), any(), any(), any(), any(Pageable.class))).thenReturn(new PageImpl<>(List.of(ExerciseSummary.from(squat), ExerciseSummary.from(bench), ExerciseSummary.from(plank))));

        mockMvc.perform(get("/api/exercises"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page.totalElements").value(3))
                .andExpect(jsonPath("$.content[0].id").value("Barbell_Squat"))
                .andExpect(jsonPath("$.content[0].force").doesNotExist())
                .andExpect(jsonPath("$.content[2].image").doesNotExist());
    }

    @Test
    void returnsSingleExercise() throws Exception {
        when(exerciseRepository.findByExerciseId(squat.getExerciseId())).thenReturn(Optional.of(squat));

        mockMvc.perform(get("/api/exercises/"+ squat.getExerciseId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Barbell Squat"));
    }

    @Test
    void returnsNotFoundForUnknownId() throws Exception {
        when(exerciseRepository.findByExerciseId("Does_Not_Exist_Lol")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/exercises/Does_Not_Exist_Lol"))
                .andExpect(status().isNotFound());
    }

    @Test
    void returnsFilterOptions() throws Exception {
        when(exerciseService.getFilterOptions()).thenReturn(new ExerciseFilterOptions(
                List.of("push", "pull"),
                List.of("dumbbell"),
                List.of("cardio", "powerlifting"),
                List.of("biceps", "chest")));

        mockMvc.perform(get("/api/exercises/filter-options"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.forces[0]").value("push"))
                .andExpect(jsonPath("$.equipment[0]").value("dumbbell"))
                .andExpect(jsonPath("$.categories[0]").value("cardio"))
                .andExpect(jsonPath("$.primaryMuscles[0]").value("biceps"));
    }
}
