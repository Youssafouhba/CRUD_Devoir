FROM tomcat:10-jdk11-openjdk


COPY target/*.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
