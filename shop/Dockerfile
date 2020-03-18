FROM maven:3.6.0-jdk-11-slim AS build
COPY src home/app/src
COPY pom.xml home/app/pom.xml
RUN mvn -f home/app/pom.xml clean package



FROM openjdk:11-jre-slim
COPY --from=build home/app/target/shop-0.0.1-SNAPSHOT.jar /usr/local/lib/shop.jar
EXPOSE 5000
ENTRYPOINT ["java","-jar","usr/local/lib/shop.jar"]
