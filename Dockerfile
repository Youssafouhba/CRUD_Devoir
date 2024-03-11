FROM tomcat:10-jdk11-openjdk AS builder
WORKDIR /app
COPY . /app/
RUN mvn clean package

WORKDIR /app
COPY --from=builder /app/target/*.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
