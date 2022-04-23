FROM adoptopenjdk/openjdk11 as finalApp
WORKDIR /app
COPY . /app
RUN ./mvnw clean package
COPY target/salestaxes.jar /app/salestaxes.jar
EXPOSE 8088
ENTRYPOINT ["java","-jar", "salestaxes.jar"]
