FROM openjdk:8-jre-slim

WORKDIR /app
COPY ./customTarget/logogen-0.0.1-SNAPSHOT.war /app

EXPOSE 8080

CMD ["java", "-war", "logogen-0.0.1-SNAPSHOT.war"]