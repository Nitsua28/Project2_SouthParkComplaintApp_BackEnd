#
# Build stage
#
# Using Maven as our base image
FROM maven:3.6.0-jdk-11-slim AS build
# Copy the src folder into the container
COPY src /home/app/src
# Copy the pom file into the container
COPY pom.xml /home/app

# Mackage the maven app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
# Using Java as base image
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/spring-boot-demo-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
# Expose port 8080 so we can access from our end:
EXPOSE 8080
# Execute when container is started:
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]