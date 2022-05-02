FROM alpine
RUN apk add --update openjdk11 maven
WORKDIR /usr/app
COPY pom.xml .
COPY ./codecheck/ ./codecheck
COPY ./coverage/ ./coverage
COPY src/it ./src/it
COPY src/test ./src/test
COPY src/main ./src/main
RUN mvn clean package
ENTRYPOINT ["java","-jar", "target/salestaxes.jar"]
