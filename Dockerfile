FROM tomcat:10-jdk11-openjdk


COPY *.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
