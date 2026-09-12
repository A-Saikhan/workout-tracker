FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /home/app
COPY pom.xml .
COPY mvnw .
COPY .mvn ./.mvn
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

COPY src ./src
RUN ./mvnw -DskipTests clean package

FROM eclipse-temurin:21-jre AS final
WORKDIR /home/app
EXPOSE 8080
COPY --from=builder /home/app/target/*.jar /home/app/app.jar
ENTRYPOINT ["java", "-jar", "/home/app/app.jar"]