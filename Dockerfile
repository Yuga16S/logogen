FROM openjdk:8-jre-slim

WORKDIR /app
COPY ./customTarget/logogen-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "logogen-0.0.1-SNAPSHOT.jar"]