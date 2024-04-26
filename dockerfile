FROM openjdk:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/principal-0.0.1-SNAPSHOT.war
COPY ${JAR_FILE} tecnoyomuapp.war
ENTRYPOINT ["java", "-jar", "/tecnoyomuapp.war"]