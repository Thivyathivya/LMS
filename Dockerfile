#FROM maven:openjdk:20-jdk
#ARG JAR_FILE=target/*.jar
#COPY ./target/iskcon-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

#FROM eclipse-temurin
#VOLUME /tmp
#COPY ./target/iskcon-0.0.1-SNAPSHOT.jar app.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "/app.jar"]
#
# Build stage
#
#FROM maven:4.0.0-jdk-20 AS build
#COPY . .
#RUN mvn clean package -DskipTests

#
# Package stage
#
#FROM openjdk:20
#COPY --from=build /target/iskcon-0.0.1-SNAPSHOT.jar demo.jar
# ENV PORT=8080
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","demo.jar"]
# Build stage


# Package stage
#FROM eclipse-temurin:17-jdk-alpine
#COPY target/*.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

#Docker Image
FROM openjdk:20
ADD ./userManagement-0.0.1-SNAPSHOT.jar  userManagement-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","userManagement-0.0.1-SNAPSHOT.jar"]