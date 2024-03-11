FROM tomcat:10-jdk11-openjdk
WORKDIR /app
COPY . /app/
COPY target/*.war /app/app.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
