FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} session-service.jar
ENTRYPOINT ["java", "-jar", "session-service.jar"]