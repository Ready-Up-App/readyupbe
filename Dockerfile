# Use an openjdk image as the base image
FROM openjdk:17-jdk-slim AS builder

# Set the working directory
WORKDIR /app

# Copy the pom.xml and download dependencies (this helps with caching)
COPY .mvn/ .mvn
COPY pom.xml .
COPY mvnw ./mvnw

RUN sed -i 's/\r$//' mvnw

# Copy the source code
COPY ./api api
COPY ./domain domain
COPY ./jar jar
COPY manager manager
COPY ri ri
COPY security ./security


RUN ./mvnw dependency:go-offline


# Package the application
RUN ./mvnw clean package -DskipTests

# Use openjdk to run the Spring Boot application
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the jar from the builder image
COPY --from=builder /app/jar/target/*.jar /app/jar/target/
# Expose the port your Spring Boot app runs on (default is 8080)
EXPOSE 8080

# Command to run the app
ENTRYPOINT ["java", "-jar", "/app/jar/target/readyup.jar"]
