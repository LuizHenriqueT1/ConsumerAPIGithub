FROM maven:3.8.7-jdk-18 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:18-jdk-slim
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar demo.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]
