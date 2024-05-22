FROM openjdk:17
COPY build/libs/back-0.0.1-SNAPSHOT.jar back-member.jar
ENTRYPOINT ["java", "-jar", "back-member.jar"]
