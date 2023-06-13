FROM openjdk:20-jdk-slim
VOLUME /tmp
ADD spring-boot-docker-1.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]