FROM maven:3.8.3-jdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jre
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar demo.jar
ENV PORT=8080
EXPOSE $PORT
ENTRYPOINT ["java","-jar","demo.jar"]
