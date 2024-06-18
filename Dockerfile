FROM openjdk:17
COPY build/libs/back-0.0.1-SNAPSHOT.jar back-data.jar
ENTRYPOINT ["java", "-jar", "back-data.jar"]
