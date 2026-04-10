FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

ARG GITHUB_ACTOR
ARG GITHUB_TOKEN

ENV GITHUB_ACTOR=${GITHUB_ACTOR}
ENV GITHUB_TOKEN=${GITHUB_TOKEN}

COPY mvnw .
COPY mvnw.cmd .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN apk add --no-cache maven

RUN mkdir -p ~/.m2 && \
    cat > ~/.m2/settings.xml << EOF \
<?xml version="1.0" encoding="UTF-8"?> \
<settings> \
  <servers> \
    <server> \
      <id>github</id> \
      <username>\${env.GITHUB_ACTOR}</username> \
      <password>\${env.GITHUB_TOKEN}</password> \
    </server> \
  </servers> \
</settings>
EOF

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
