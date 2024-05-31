FROM openjdk:17
COPY build/libs/back-0.0.1-SNAPSHOT.jar back-auth.jar
ENTRYPOINT ["java", "-jar", "back-auth.jar"]
