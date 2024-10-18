FROM amazoncorretto:18.0.2-alpine
WORKDIR /app
COPY src/main/resources /app/src/main/resources
COPY build/libs/FullStackLab1-1.0-SNAPSHOT.jar /app/FullStackLab1-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/FullStackLab1-1.0-SNAPSHOT.jar"]
