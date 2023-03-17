FROM openjdk:17-jdk-alpine3.14
WORKDIR /app
COPY ./target/*.jar /app/
CMD ["sh", "-c", "java -jar /app/*.jar"]