# Use an official OpenJDK 21 image as the base
FROM openjdk:21-jdk-slim

LABEL authors="heinhtetzaw"

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/user-service.jar user-service.jar

# Expose the port that the service runs on
EXPOSE 8081

# Run the JAR file
ENTRYPOINT ["java", "-jar", "user-service.jar"]