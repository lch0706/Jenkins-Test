FROM gradle:jdk11-hotspot as build-stage

WORKDIR /app
COPY . .
RUN gradle bootJar

FROM adoptopenjdk/openjdk11 as production-stage
COPY --from=build-stage /app/build/libs/*.jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]