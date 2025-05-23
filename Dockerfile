FROM openjdk:17-jdk-alpine
COPY target/BotsCrew-0.0.1-SNAPSHOT.jar BotsCrew.jar
ENTRYPOINT ["java","-jar","BotsCrew.jar"]