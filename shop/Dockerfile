FROM maven:3.6.0-jdk-11-slim AS build
COPY src home/app/src
COPY pom.xml home/app/pom.xml
RUN mvn -f home/app/pom.xml clean install



FROM openjdk:11-jre-slim
ADD home/apptarget/shop-0.0.1-SNAPSHOT.jar shop.jar
EXPOSE 5000
ENTRYPOINT ["java","-jar","home/app/target/shop.jar"]
