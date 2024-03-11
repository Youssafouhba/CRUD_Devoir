FROM eclipse-temurin:20-jdk-alpine
VOLUME /tmp
COPY target/*.war app.war
ENTRYPOINT ["java","-war","/app.war"]
