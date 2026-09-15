package com.saikhan.workout_tracker.controller;


import com.saikhan.workout_tracker.model.Exercise;
import com.saikhan.workout_tracker.repository.ExerciseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.RestTestClient;
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
        when(exerciseRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(squat, bench, plank)));

        mockMvc.perform(get("/api/exercises"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(3));
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
}
