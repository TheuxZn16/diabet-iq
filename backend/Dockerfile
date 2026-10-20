FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn -B -DskipTests dependency:go-offline

COPY src ./src
RUN mvn -B -DskipTests package

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

RUN addgroup -S diabetiq && adduser -S diabetiq -G diabetiq

COPY --from=build /app/target/*.jar app.jar

USER diabetiq

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
