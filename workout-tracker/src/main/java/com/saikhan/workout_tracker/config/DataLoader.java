package com.saikhan.workout_tracker.config;

import com.saikhan.workout_tracker.model.Exercise;
import com.saikhan.workout_tracker.repository.ExerciseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final ExerciseRepository exerciseRepository;

    public DataLoader(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("DataLoader started");
        // todo: rewrite the check later
        if (exerciseRepository.count() > 0) {
            System.out.println("Data already imported, skipping parsing");
            return;
        }

        // json parser
        ClassPathResource resource = new ClassPathResource("data/exercises.json");
        InputStream inputStream = resource.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        List<Exercise> exercises = mapper.readValue(inputStream, new TypeReference<List<Exercise>>() {
        });

        try {
            exerciseRepository.saveAll(exercises);
            System.out.println("Imported " + exercises.size() + " exercises");
        } catch (Exception e) {
            System.out.println("Import failed: " + e.getMessage());
        }
    }
}
