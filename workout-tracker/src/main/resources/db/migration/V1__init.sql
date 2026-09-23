--
-- PostgreSQL database dump

-- Ininital Schema, created from pg_dump from 23.09.2026

--
-- Name: exercise; Type: TABLE; Schema: public; Owner: workout
--

CREATE TABLE public.exercise (
    id bigint NOT NULL,
    category character varying(255),
    equipment character varying(255),
    exercise_id character varying(255),
    force character varying(255),
    level character varying(255),
    mechanic character varying(255),
    name character varying(255)
);




--
-- Name: exercise_images; Type: TABLE; Schema: public; Owner: workout
--

CREATE TABLE public.exercise_images (
    exercise_id bigint NOT NULL,
    images character varying(255)
);




--
-- Name: exercise_instructions; Type: TABLE; Schema: public; Owner: workout
--

CREATE TABLE public.exercise_instructions (
    exercise_id bigint NOT NULL,
    instructions character varying(2000)
);




--
-- Name: exercise_primary_muscles; Type: TABLE; Schema: public; Owner: workout
--

CREATE TABLE public.exercise_primary_muscles (
    exercise_id bigint NOT NULL,
    primary_muscles character varying(255)
);




--
-- Name: exercise_secondary_muscles; Type: TABLE; Schema: public; Owner: workout
--

CREATE TABLE public.exercise_secondary_muscles (
    exercise_id bigint NOT NULL,
    secondary_muscles character varying(255)
);




--
-- Name: exercise_seq; Type: SEQUENCE; Schema: public; Owner: workout
--

CREATE SEQUENCE public.exercise_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- Name: exercise_set; Type: TABLE; Schema: public; Owner: workout
--

CREATE TABLE public.exercise_set (
    id bigint NOT NULL,
    reps integer NOT NULL,
    weight double precision NOT NULL
);




--
-- Name: exercise_set_seq; Type: SEQUENCE; Schema: public; Owner: workout
--

CREATE SEQUENCE public.exercise_set_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- Name: workout; Type: TABLE; Schema: public; Owner: workout
--

CREATE TABLE public.workout (
    id bigint NOT NULL,
    date timestamp(6) without time zone
);




--
-- Name: workout_exercise; Type: TABLE; Schema: public; Owner: workout
--

CREATE TABLE public.workout_exercise (
    id bigint NOT NULL,
    exercise_id bigint
);




--
-- Name: workout_exercise_seq; Type: SEQUENCE; Schema: public; Owner: workout
--

CREATE SEQUENCE public.workout_exercise_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- Name: workout_exercise_sets; Type: TABLE; Schema: public; Owner: workout
--

CREATE TABLE public.workout_exercise_sets (
    workout_exercise_id bigint NOT NULL,
    sets_id bigint NOT NULL
);




--
-- Name: workout_exercises; Type: TABLE; Schema: public; Owner: workout
--

CREATE TABLE public.workout_exercises (
    workout_id bigint NOT NULL,
    exercises_id bigint NOT NULL
);




--
-- Name: workout_seq; Type: SEQUENCE; Schema: public; Owner: workout
--

CREATE SEQUENCE public.workout_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- Name: exercise exercise_pkey; Type: CONSTRAINT; Schema: public; Owner: workout
--

ALTER TABLE ONLY public.exercise
    ADD CONSTRAINT exercise_pkey PRIMARY KEY (id);


--
-- Name: exercise_set exercise_set_pkey; Type: CONSTRAINT; Schema: public; Owner: workout
--

ALTER TABLE ONLY public.exercise_set
    ADD CONSTRAINT exercise_set_pkey PRIMARY KEY (id);


--
-- Name: workout_exercise_sets uk1jy2ik7pdre3d74p8g3hnpna6; Type: CONSTRAINT; Schema: public; Owner: workout
--

ALTER TABLE ONLY public.workout_exercise_sets
    ADD CONSTRAINT uk1jy2ik7pdre3d74p8g3hnpna6 UNIQUE (sets_id);


--
-- Name: workout_exercises ukg2t5net0naqu6p8v33lv7mbjt; Type: CONSTRAINT; Schema: public; Owner: workout
--

ALTER TABLE ONLY public.workout_exercises
    ADD CONSTRAINT ukg2t5net0naqu6p8v33lv7mbjt UNIQUE (exercises_id);


--
-- Name: workout_exercise workout_exercise_pkey; Type: CONSTRAINT; Schema: public; Owner: workout
--

ALTER TABLE ONLY public.workout_exercise
    ADD CONSTRAINT workout_exercise_pkey PRIMARY KEY (id);


--
-- Name: workout workout_pkey; Type: CONSTRAINT; Schema: public; Owner: workout
--

ALTER TABLE ONLY public.workout
    ADD CONSTRAINT workout_pkey PRIMARY KEY (id);


--
-- Name: workout_exercises fk8mqm00xeg12xr6hgfqiopphcq; Type: FK CONSTRAINT; Schema: public; Owner: workout
--

ALTER TABLE ONLY public.workout_exercises
    ADD CONSTRAINT fk8mqm00xeg12xr6hgfqiopphcq FOREIGN KEY (exercises_id) REFERENCES public.workout_exercise(id);


--
-- Name: exercise_primary_muscles fk906q6pdmx36pv1vjh9r6sept4; Type: FK CONSTRAINT; Schema: public; Owner: workout
--

ALTER TABLE ONLY public.exercise_primary_muscles
    ADD CONSTRAINT fk906q6pdmx36pv1vjh9r6sept4 FOREIGN KEY (exercise_id) REFERENCES public.exercise(id);


--
-- Name: exercise_instructions fkahv78nygw23r7wd7esrygqy1v; Type: FK CONSTRAINT; Schema: public; Owner: workout
--

ALTER TABLE ONLY public.exercise_instructions
    ADD CONSTRAINT fkahv78nygw23r7wd7esrygqy1v FOREIGN KEY (exercise_id) REFERENCES public.exercise(id);


--
-- Name: workout_exercise fkalytxvdcpsg2e2oo8ihk55dm2; Type: FK CONSTRAINT; Schema: public; Owner: workout
--

ALTER TABLE ONLY public.workout_exercise
    ADD CONSTRAINT fkalytxvdcpsg2e2oo8ihk55dm2 FOREIGN KEY (exercise_id) REFERENCES public.exercise(id);


--
-- Name: workout_exercise_sets fkcrm8aanxhhskgi8w24pgvrnbb; Type: FK CONSTRAINT; Schema: public; Owner: workout
--

ALTER TABLE ONLY public.workout_exercise_sets
    ADD CONSTRAINT fkcrm8aanxhhskgi8w24pgvrnbb FOREIGN KEY (sets_id) REFERENCES public.exercise_set(id);


--
-- Name: exercise_images fkfd38icde3ic140cio5j63gtyg; Type: FK CONSTRAINT; Schema: public; Owner: workout
--

ALTER TABLE ONLY public.exercise_images
    ADD CONSTRAINT fkfd38icde3ic140cio5j63gtyg FOREIGN KEY (exercise_id) REFERENCES public.exercise(id);


--
-- Name: exercise_secondary_muscles fkfmc6n3e0yosbeqacew1wu4m2x; Type: FK CONSTRAINT; Schema: public; Owner: workout
--

ALTER TABLE ONLY public.exercise_secondary_muscles
    ADD CONSTRAINT fkfmc6n3e0yosbeqacew1wu4m2x FOREIGN KEY (exercise_id) REFERENCES public.exercise(id);


--
-- Name: workout_exercise_sets fklkcc41lb0mwypg3i481wgwvxo; Type: FK CONSTRAINT; Schema: public; Owner: workout
--

ALTER TABLE ONLY public.workout_exercise_sets
    ADD CONSTRAINT fklkcc41lb0mwypg3i481wgwvxo FOREIGN KEY (workout_exercise_id) REFERENCES public.workout_exercise(id);


--
-- Name: workout_exercises fksi19utmvl26jimb7c6ypabrqs; Type: FK CONSTRAINT; Schema: public; Owner: workout
--

ALTER TABLE ONLY public.workout_exercises
    ADD CONSTRAINT fksi19utmvl26jimb7c6ypabrqs FOREIGN KEY (workout_id) REFERENCES public.workout(id);


--
-- PostgreSQL database dump complete
--

