FROM eclipse-temurin:21.0.9_10-jre-ubi9-minimal

LABEL maintainer="sehmusaydogdu47@gmail.com"

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]