FROM maven:3-amazoncorretto-17 AS extensions

WORKDIR /app

COPY pom.xml .
COPY src src

RUN mvn package && mv target/keycloak-theme-additional-info-extension.jar keycloak-theme-additional-info-extension.jar
