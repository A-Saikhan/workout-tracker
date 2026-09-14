# Workout Tracker

A workout tracking API for logging exercises and following your progress over time.

> **Status:** Work in progress. The exercise catalogue (873 exercises) is fully
> functional. Workout logging is scaffolded but not active yet - the endpoints
> respond with empty results while authentication is still being built.

## Links

- [Live demo](https://workout.saikhan.dev)
- [Frontend repository](https://github.com/A-Saikhan/workout-tracker-frontend)

![Exercise List](docs/preview.png)

## Tech stack

Spring Boot backend on Java 21, using Spring Data JPA against PostgreSQL 18.
The whole stack runs in Docker Compose and is served behind nginx in production.

## API

| Method | URL                 | Description          |
|--------|---------------------|----------------------|
| GET    | /api/exercises      | Exercises, paginated |
| GET    | /api/exercises/{id} | Single exercise      |
| GET    | /api/workouts       | All workouts         |
| GET    | /api/workouts/{id}  | Single workout       |

The list endpoints use Spring Data pagination, so `page` and `size` are
available as query parameters:

```
/api/exercises?page=0&size=20
```

The workout endpoints return empty results for now. They are in place so the
frontend can be developed against them, but they stay empty until authentication
lands.

## Running it locally

### With Docker

Install Docker and Docker Compose first - the [official docs](https://docs.docker.com/desktop/)
cover every platform.

Clone the repository and move into the project folder:

```bash
git clone https://github.com/A-Saikhan/workout-tracker
cd workout-tracker/workout-tracker
```

Copy the example environment file and adjust it. It ships with sane defaults for
local development, but **change the password** before you use it anywhere else:

```bash
cp .env.example .env
```

Start the stack:

```bash
docker compose up -d
```

Docker pulls PostgreSQL, builds the backend and starts both. On the first run the
seeder imports 873 exercises into the database, which takes a few seconds. Once it
finishes, the API is available on `localhost:8080`.

Check that it works:

```bash
curl "localhost:8080/api/exercises?size=5"
```

### Without Docker

**Requirements:** You need JDK 21 and a running PostgreSQL 18 instance.

Clone the repository:
```bash
git clone https://github.com/A-Saikhan/workout-tracker
cd workout-tracker/workout-tracker
```
The quickest way to get a database is a single container. Data is lost when you remove it,
which is fine for development:

```bash
docker run -d --name workout-db -e POSTGRES_DB=workout_tracker -e POSTGRES_USER=workout -e POSTGRES_PASSWORD=changeme -p 127.0.0.1:5432:5432 postgres:18
```

**Set the environment variables before starting the backend:**
The host is `localhost` here, with Docker Compose it would be `postgres`,
the service name:
```
DB_URL=jdbc:postgresql://localhost:5432/workout_tracker
DB_USERNAME=workout
DB_PASSWORD=changeme
```

How you set them depends on your setup. `export` in the shell,
or the run configuration of your IDE.

An example for IntelliJ would be: `DB_URL=jdbc:postgresql://localhost:5432/workout_tracker;DB_USERNAME=workout;DB_PASSWORD=changeme`

Start the application:
```bash
./mvnw spring-boot:run
```

Done. The API is available on `localhost:8080`.

Check that it works:

```bash
curl "localhost:8080/api/exercises?size=5"
```

To remove it, use:
```bash
docker rm -f workout-db
```


## Notes on the setup

**In the Docker setup, PostgreSQL is not reachable from outside the container network.** There is no
`ports` entry for it in the compose file, which is intentional. To get a shell on
the database, go through the container:

```bash
docker compose exec postgres psql -U <user> workout_tracker
```

**The backend binds to `127.0.0.1` only.** That keeps it off the network, but it
also means you cannot reach it from another device such as a phone. If you want to
test from your phone on the same network, change the port mapping in
`docker-compose.yml` to `8080:8080` - locally only, never in production.

## Credits

Exercise data comes from [free-exercise-db](https://github.com/yuhonas/free-exercise-db),
released into the public domain.

## License

Released under the MIT License. See [LICENSE](LICENSE) for details.