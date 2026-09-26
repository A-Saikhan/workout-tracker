ALTER TABLE exercise ALTER COLUMN exercise_id SET NOT NULL;
ALTER TABLE exercise ADD CONSTRAINT uk_exercise_exercise_id UNIQUE (exercise_id);