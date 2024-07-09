# Stage 1: Build the application with Maven
FROM jelastic/maven:3.9.5-openjdk-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the final image
FROM openjdk:21-jdk-slim AS runner
WORKDIR /app
COPY --from=builder /app/target/mavenproject1-1.0-SNAPSHOT.jar mavenproject1-1.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "mavenproject1-1.0-SNAPSHOT.jar"]