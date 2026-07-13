FROM eclipse-temurin:21-jdk AS builder
ARG SERVICE_NAME
WORKDIR /app
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY common common
COPY ${SERVICE_NAME} ${SERVICE_NAME}
RUN chmod +x gradlew && ./gradlew :${SERVICE_NAME}:bootJar -x test

FROM eclipse-temurin:21-jre
ARG SERVICE_NAME
WORKDIR /app
COPY --from=builder /app/${SERVICE_NAME}/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
