FROM openjdk:8-jre-alpine
ADD user*.jar app.jar
EXPOSE 18100
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]