FROM openjdk:20-jdk-slim
VOLUME /tmp

# create app directory
RUN mkdir -p /app
WORKDIR /app

# create jar file


ENTRYPOINT ["java","-jar","/app.jar"]