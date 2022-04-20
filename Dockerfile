FROM openjdk:11 AS build

RUN mkdir -p /app

WORKDIR /app/

COPY ./ /app/

RUN /app/mvnw clean package verify

COPY ./target/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]


