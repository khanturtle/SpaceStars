FROM openjdk:17
COPY build/libs/back-0.0.1-SNAPSHOT.jar back-matching.jar
ENTRYPOINT ["java", "-jar", "back-matching.jar"]
