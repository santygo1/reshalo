FROM openjdk:21-slim as build

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)

FROM openjdk:21-slim

VOLUME /tmp

ARG DEPENDENCY=/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

EXPOSE 8080

ENTRYPOINT ["java", "-cp", "app:app/lib/*", "ru.danilkaspirin.reshalo.ReshaloApplication"]