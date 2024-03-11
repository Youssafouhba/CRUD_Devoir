FROM tomcat:10-jdk11-openjdk
WORKDIR /app
COPY . /app/
COPY target/*.jar /app/app.jar

EXPOSE 8080
CMD ["catalina.sh", "run"]
