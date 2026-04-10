FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

ARG MODEL_VERSION=1.0.0-SNAPSHOT
ARG GITHUB_ACTOR
ARG GITHUB_TOKEN

ENV MODEL_VERSION=${MODEL_VERSION}
ENV GITHUB_ACTOR=${GITHUB_ACTOR}
ENV GITHUB_TOKEN=${GITHUB_TOKEN}

COPY mvnw .mvn/ pom.xml settings.xml ./
COPY src src

RUN apk add --no-cache maven && \
    mkdir -p ~/.m2 && \
    cp settings.xml ~/.m2/settings.xml && \
    mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
