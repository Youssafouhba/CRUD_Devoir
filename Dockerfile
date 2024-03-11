FROM eclipse-temurin:20-jdk-alpine
VOLUME /tmp
COPY target/*.jar.original app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
