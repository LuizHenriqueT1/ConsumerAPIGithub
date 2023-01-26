FROM maven:3.6.3-jdk-11 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11-jdk-slim
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar demo.jar
ENV PORT=8080
EXPOSE $PORT
ENTRYPOINT ["java","-jar","demo.jar"]
