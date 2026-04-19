FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

CMD ["java","-jar","target/*.jar"]