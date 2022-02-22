FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} project-service.jar
ENTRYPOINT ["java", "-jar", "project-service.jar"]