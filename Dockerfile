FROM openjdk:17-jdk-alpine3.14
WORKDIR /app
COPY ./target/*.jar /app/
CMD ["sh", "-c", "java -jar /app/Producer1-0.0.1-SNAPSHOT.jar & java -jar /app/consumer2producer3-0.0.1-SNAPSHOT.jar & java -jar /app/consumer3producer2-0.0.1-SNAPSHOT.jar & java -jar /app/Consumer1-0.0.1-SNAPSHOT.jar"]