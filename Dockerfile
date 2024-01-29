FROM openjdk:8-jre-slim

WORKDIR /app
COPY ./customTarget/logogen-0.0.1-SNAPSHOT.jar /app

ADD /src/main/resources/application-docker.properties /app/application.properties

EXPOSE 8080

CMD ["java", "-jar", "logogen-0.0.1-SNAPSHOT.jar", "--spring.config.location=classpath:file:/app/application.properties"]