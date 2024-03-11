FROM tomcat:10-jdk11-openjdk

COPY src src
COPY *.jar app.jar

EXPOSE 8080
CMD ["catalina.sh", "run"]
