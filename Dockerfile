FROM openjdk:23-jdk-slim

WORKDIR /app

COPY target/vic-hab-sync.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]