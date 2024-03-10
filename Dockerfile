# Use a multi-stage build for Maven dependencies
FROM maven:3.8.4-openjdk-11 AS builder

# Set the working directory
WORKDIR /app

# Copy the pom.xml file to the container
COPY pom.xml .

# Download the project dependencies
RUN mvn dependency:go-offline -B

# Copy the project source code
COPY src ./src

# Build the application
RUN mvn package

# Create a new stage for the final image
FROM openjdk:11

# Set the working directory
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /target/target/CRUD-1.0-SNAPSHOT.war ./app.war

# Set the command to run the application
CMD ["java", "-war", "app.war"]
