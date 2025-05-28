# Use Maven image with Eclipse Temurin JDK 21 for building the app
FROM maven:3.9.9-eclipse-temurin-21 AS builder

# Set working directory inside the container
WORKDIR /app

# Copy the Maven project file
COPY pom.xml .

# Download dependencies to speed up build cache
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src ./src

# Build the application and generate the JAR
RUN mvn clean package

# Use OpenJDK 21 runtime for running the app (no Maven here)
FROM openjdk:21-jdk AS runner

# Set working directory
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder ./app/target/patient-service-0.0.1-SNAPSHOT.jar ./app.jar

# Expose port 4000
EXPOSE 4000

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
