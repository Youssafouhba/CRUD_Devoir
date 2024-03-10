FROM maven:3.8.5 as maven

WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn package

FROM tomcat:10.1.18-jdk20-openjdk-oracle
VOLUME /src
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} hi.jar
ENTRYPOINT ["java","-jar","hi.jar"]
