FROM openjdk:21-slim as build

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-Dpolyglotimpl.DisableClassPathIsolation=true", "-jar", "app.jar"]